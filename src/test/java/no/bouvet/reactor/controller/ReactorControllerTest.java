package no.bouvet.reactor.controller;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import no.bouvet.reactor.RoutingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(
  classes = {RoutingApplication.class},
  webEnvironment=WebEnvironment.DEFINED_PORT
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Log4j2
class ReactorControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  int port;

  @Test
  void testStartProcess() {
    val url = "http://localhost:" + port + "/api/v1/start";

    HttpHeaders headers = new HttpHeaders();

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
    assertThat(response.getStatusCode().value()).isEqualTo(200);
  }
}