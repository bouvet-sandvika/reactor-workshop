package no.bouvet.reactor.util;

import lombok.Getter;

@Getter
public class ErrorMessage {

  private final String errorMessage;

  public ErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
