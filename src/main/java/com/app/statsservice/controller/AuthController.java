package com.app.statsservice.controller;

import com.app.statsservice.dto.LoginRequest;
import com.app.statsservice.dto.RegisterRequest;
import com.app.statsservice.service.AuthService;
import com.app.statsservice.service.AuthenticationResponse;
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
    authService.signup(registerRequest);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }
}
