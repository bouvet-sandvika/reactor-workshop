package no.bouvet.reactor.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import no.bouvet.reactor.dao.EnturDao;
import no.bouvet.reactor.dao.EnturDaoImpl;
import no.bouvet.reactor.model.stopplaces.StopPlace;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

@Service
@Log4j2
public class EnturService  {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

   /* @Autowired
    private static EnturDao enturDao;*/

    public Flux<StopPlace> sendStopPlaces() {
       return null;
    }

    public static class KafkaSink extends AbstractScenario {
        private final String topic;

        public KafkaSink(String bootstrapServers, String topic) {
            super(bootstrapServers);
            this.topic = topic;
        }
        public Flux<?> flux() {
            SenderOptions<String, StopPlace> senderOptions = senderOptions()
                    .producerProperty(ProducerConfig.ACKS_CONFIG, "all")
                    .producerProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, Long.MAX_VALUE)
                    .producerProperty(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
            Flux<StopPlace> srcFlux = source().flux();
            return sender(senderOptions)
                    .send(srcFlux.map(p -> SenderRecord.create(new ProducerRecord<>(topic, p.getId(), p), p.getId())))
                    .doOnError(e -> log.error("Send failed, terminating.", e))
                    .doOnNext(r -> {
                        String id = r.correlationMetadata();
                        log.trace("Successfully stored StopPlace with id {} in Kafka", id);
                        source.commit(id);
                    })
                    .doOnCancel(this::close);
        }
    }

    public static class KafkaSource extends AbstractScenario {
        private final String topic;
        private final Scheduler scheduler;

        public KafkaSource(String bootstrapServers, String topic) {
            super(bootstrapServers);
            this.topic = topic;
            this.scheduler = Schedulers.newSingle("sample", true);
        }
        public Flux<?> flux() {
            return KafkaReceiver.create(receiverOptions(Collections.singletonList(topic)).commitInterval(Duration.ZERO))
                    .receive()
                    .publishOn(scheduler)
                    .concatMap(m -> storeInDB(m.value())
                            .thenEmpty(m.receiverOffset().commit()))
                    .retry()
                    .doOnCancel(() -> close());
        }
        public Mono<Void> storeInDB(StopPlace stopPlace) {
            log.info("Successfully processed stopPlace with id {} from Kafka", stopPlace.getId());
            return Mono.empty();
        }
        public void close() {
            super.close();
            scheduler.dispose();
        }

    } 

    public static class StopPlaceSerDes implements Serializer<StopPlace>, Deserializer<StopPlace> {

        private Gson gson = new Gson();

        @Override
        public void configure(Map<String, ?> configs, boolean isKey) {
        }

        @Override
        public byte[] serialize(String topic, StopPlace StopPlace) {
            Type typeObject = new TypeToken<StopPlace>() {}.getType();
            return gson.toJson(StopPlace, typeObject).getBytes(StandardCharsets.UTF_8);
        }

        @Override
        public StopPlace deserialize(String topic, byte[] data) {
            String stopPlaceJson = new String(data, StandardCharsets.UTF_8);
            JsonElement jsonElement = JsonParser.parseString(stopPlaceJson);

            Type typeObject = new TypeToken<StopPlace>() {}.getType();
            return gson.fromJson(jsonElement,typeObject);
        }

        @Override
        public void close() {
        }
    }

    static class CommittableSource {

        private List<StopPlace> sourceList = new ArrayList<>();
        CommittableSource(EnturDao enturDao) {
            sourceList = enturDao.getStopPlaces();
        }
        CommittableSource(List<StopPlace> list) {
            sourceList.addAll(list);
        }
        Flux<StopPlace> flux() {
            return Flux.fromIterable(sourceList);
        }

        public List<StopPlace> getSoruceList() {
            return sourceList;
        }

        void commit(String id) {
            log.trace("Committing {}", id);
        }
    }

    public static abstract class AbstractScenario {
        String bootstrapServers = BOOTSTRAP_SERVERS;
        String groupId = "sample-group";
        CommittableSource source;
        KafkaSender<String, StopPlace> sender;
        List<Disposable> disposables = new ArrayList<>();

        AbstractScenario(String bootstrapServers) {
            this.bootstrapServers = bootstrapServers;
        }
        public abstract Flux<?> flux();

        public void runScenario() throws InterruptedException {
            flux().blockLast();
            close();
        }

        public void close() {
            if (sender != null)
                sender.close();
            for (Disposable disposable : disposables)
                disposable.dispose();
        }

        public SenderOptions<String, StopPlace> senderOptions() {
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            props.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
            props.put(ProducerConfig.ACKS_CONFIG, "all");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StopPlaceSerDes.class);
            return SenderOptions.create(props);
        }

        public KafkaSender<String, StopPlace> sender(SenderOptions<String, StopPlace> senderOptions) {
            sender = KafkaSender.create(senderOptions);
            return sender;
        }

        public ReceiverOptions<String, StopPlace> receiverOptions() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            props.put(ConsumerConfig.CLIENT_ID_CONFIG, "sample-consumer");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StopPlaceSerDes.class);
            return ReceiverOptions.<String, StopPlace>create(props);
        }

        public ReceiverOptions<String, StopPlace> receiverOptions(Collection<String> topics) {
            return receiverOptions()
                    .addAssignListener(p -> log.info("Group {} partitions assigned {}", groupId, p))
                    .addRevokeListener(p -> log.info("Group {} partitions revoked {}", groupId, p))
                    .subscription(topics);
        }

        public void source(CommittableSource source) {
            this.source = source;
        }

        public CommittableSource source() {
            return source;
        }
    }

}
