package no.bouvet.reactor.service;

import lombok.extern.log4j.Log4j2;
import lombok.val;
import no.bouvet.reactor.RoutingApplication;
import no.bouvet.reactor.dao.EnturDao;
import no.bouvet.reactor.model.stopplaces.StopPlace;
import no.bouvet.reactor.util.TestUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import no.bouvet.reactor.service.EnturService.KafkaSource;
import no.bouvet.reactor.service.EnturService.KafkaSink;
import no.bouvet.reactor.service.EnturService.CommittableSource;


import java.time.Duration;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(
        classes = {RoutingApplication.class},
        webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@Log4j2
class EnturServiceTest {
    public static final int DEFAULT_TEST_TIMEOUT = 60_000;
    
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Autowired
    private  EnturDao enturDao;
    @Autowired
    private EnturService enturService;
    
    private final String stopPlacesTopic = "stop_places";

    private final List<Disposable> disposables = new ArrayList<>();
    
    @AfterEach
    public void tearDown() {
        disposables.forEach(Disposable::dispose);
    }

    @Test
    public void kafkaSink() throws Exception {
        val commitableSource = new CommittableSource(enturDao);
        List<StopPlace> expected = commitableSource.getSoruceList();
        List<StopPlace> received = new ArrayList<>();
        subscribeToDestTopic("test-group", stopPlacesTopic, received);
        KafkaSink sink = new KafkaSink(bootstrapServers, stopPlacesTopic);
        sink.source(commitableSource);
        sink.runScenario();
        waitForMessages(expected, received);
    }

    @Test
    public void kafkaSource() throws Exception {
        List<StopPlace> expected = new CommittableSource(enturDao).getSoruceList();
        List<StopPlace> received = new CopyOnWriteArrayList<>();
        KafkaSource source = new KafkaSource(bootstrapServers, stopPlacesTopic) {
            @Override
            public Mono<Void> storeInDB(StopPlace stopPlace) {
                received.add(stopPlace);
                return Mono.empty();
            }
            @Override
            public ReceiverOptions<String, StopPlace> receiverOptions() {
                return super.receiverOptions().consumerProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            }
        };
        disposables.add(source.flux().subscribe());
        sendMessages(stopPlacesTopic);
        waitForMessages(expected, received);
    }

    private void subscribeToDestTopic(String groupId, String topic, List<StopPlace> received) {
        KafkaSource source = new EnturService.KafkaSource(bootstrapServers, topic);
        subscribeToDestTopic(groupId, topic, source.receiverOptions(), received);
    }

    private void subscribeToDestTopic(String groupId, String topic, ReceiverOptions<String, StopPlace> receiverOptions, List<StopPlace> received) {
        receiverOptions = receiverOptions
                .consumerProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
                .consumerProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId)
                .maxDelayRebalance(Duration.ZERO)
                .addAssignListener(partitions -> {
                    log.debug("Group {} assigned {}", groupId, partitions);
                    partitions.forEach(p -> log.trace("Group {} partition {} position {}", groupId, p, p.position()));
                })
                .addRevokeListener(p -> log.debug("Group {} revoked {}", groupId, p));
        Disposable c = KafkaReceiver.create(receiverOptions.subscription(Collections.singleton(topic)))
                .receive()
                .subscribe(m -> {
                    StopPlace p = m.value();
                    received.add(p);
                    log.debug("Thread {} Received from {}: {} ", Thread.currentThread().getName(), m.topic(), p);
                });
        disposables.add(c);
    }

    private void sendMessages(String topic) throws Exception {
        KafkaSink sink = new KafkaSink(bootstrapServers, topic);
        sink.source(new CommittableSource(enturDao));
        sink.runScenario();
    }

    private void waitForMessages(Collection<StopPlace> expected, Collection<StopPlace> received) throws Exception {
        try {
            TestUtils.waitUntil("Incorrect number of messages received, expected=" + expected.size() + ", received=",
                    () -> received.size(), r -> r.size() >= expected.size(), received, Duration.ofMillis(DEFAULT_TEST_TIMEOUT));
        } catch (Error e) {
            TestUtils.printStackTrace(".*group.*");
            throw e;
        }

        expected.forEach(expectedStopPlace  -> {
            val receivedStop = received.stream()
                            .filter(receivedStopPlace -> receivedStopPlace.getId().equals(expectedStopPlace.getId()))
                            .findFirst()
                            .orElseThrow(() ->
                                    new AssertionError("Stop place: " +
                                            expectedStopPlace.getId() +
                                            " has not been received"));
            assertThat(expectedStopPlace.getId()).isEqualTo(receivedStop.getId());
        });
    }
}