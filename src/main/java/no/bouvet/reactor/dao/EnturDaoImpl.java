package no.bouvet.reactor.dao;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import no.bouvet.reactor.model.stopplaces.StopPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
@Log4j2
public class EnturDaoImpl implements EnturDao{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<StopPlace> getStopPlaces() {
        val url = "https://api.entur.io/stop-places/v1/read/stop-places";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("ET-Client-Name", "brakar-journeyplanner");

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        log.info(Objects.requireNonNull(response.getBody()));
        JsonElement jsonElement = JsonParser.parseString(response.getBody());
        Gson gson = new Gson();
        Type typeObject = new TypeToken<List<StopPlace>>() {}.getType();
        return gson.fromJson(jsonElement,typeObject);
    }
}
