package com.app.statsservice.exception;

public class MatchNotFoundException extends RuntimeException{
  public MatchNotFoundException(String message) {
    super(message);
  }

}
