package no.bouvet.reactor.controller;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping (
  path = "/api/v1"
)
@Log4j2
public class ReactorController {


  @GetMapping("/start")
  public ResponseEntity<Integer> startProcesss() {

    final int result = 1;
    return getIntegerResponseEntity(result);
  }

  @NotNull
  private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
    if (result == 1)
      return ResponseEntity.ok().build();
    else
      return ResponseEntity.badRequest().build();
  }
}
