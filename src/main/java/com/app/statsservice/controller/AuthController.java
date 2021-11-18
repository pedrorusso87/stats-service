package com.app.statsservice.controller;

import com.app.statsservice.dto.LoginRequest;
import com.app.statsservice.dto.RegisterRequest;
import com.app.statsservice.model.entities.User;
import com.app.statsservice.model.response.RegistrationResponse;
import com.app.statsservice.service.AuthService;
import com.app.statsservice.service.response.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private AuthService authService;

  public AuthController(AuthService authService ) { this.authService = authService; }

  @PostMapping("/signup")
  public ResponseEntity signUp(@RequestBody RegisterRequest registerRequest) {
    User newUser = authService.signup(registerRequest);
    return new ResponseEntity(getRegisteredUsername(newUser), HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
    AuthenticationResponse response = this.authService.login(loginRequest);
    return new ResponseEntity<>(response, response.getHttpStatus());
  }

  private RegistrationResponse getRegisteredUsername(User user) {
    return new RegistrationResponse(user.getUsername());
  }
}
