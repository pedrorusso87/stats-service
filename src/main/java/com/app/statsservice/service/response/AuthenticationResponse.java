package com.app.statsservice.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  private String authenticationToken;
  private String username;
  private String message;
  private HttpStatus httpStatus;
}
