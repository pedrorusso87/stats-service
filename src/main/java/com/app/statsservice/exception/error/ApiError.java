package com.app.statsservice.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ApiError {

  private HttpStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;

  //User-friendly message.
  private String message;

  //Message describing the error in more detail.
  private String debugMessage;

  /*Array of sub-errors that happened. This is used for representing multiple errors in a single call
  (for instance, if multiple fields have errors)*/
  private Map<String, String> subErrors;

  private ApiError() {
    timestamp = LocalDateTime.now();
  }

  public ApiError(HttpStatus status) {
    this();
    this.status = status;
  }

  public ApiError(HttpStatus status, Throwable ex) {
    this();
    this.status = status;
    this.message = "Unexpected error";
    this.debugMessage = ex.getLocalizedMessage();
  }

  public ApiError(HttpStatus status, String message, Throwable ex) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
  }

  public ApiError(HttpStatus status, String message, Throwable ex, Map<String, String> subErrors) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
    this.subErrors = subErrors;
  }
}