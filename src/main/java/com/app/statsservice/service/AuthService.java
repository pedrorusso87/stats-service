package com.app.statsservice.service;

import com.app.statsservice.dto.LoginRequest;
import com.app.statsservice.dto.RegisterRequest;
import com.app.statsservice.model.entities.User;
import com.app.statsservice.repository.UserRepository;
import com.app.statsservice.security.JwtProvider;
import com.app.statsservice.service.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

  @Autowired
  private JwtProvider jwtProvider;

  private final UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;

  public AuthService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder,
                     AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  public void signup(RegisterRequest registerRequest) {
    User user = new User();
    user.setUsername(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(encodePassword(registerRequest.getPassword()));
    userRepository.save(user);
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  public AuthenticationResponse login(LoginRequest loginRequest) {
    try{
      Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          loginRequest.getUsername(),
          loginRequest.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authenticate);
      AuthenticationResponse response = new AuthenticationResponse(jwtProvider.generateToken(authenticate), loginRequest.getUsername(),
          "Success", HttpStatus.OK);
      return response;
    } catch (BadCredentialsException e) {
      return buildAuthtenticationResponse();
    }
  }

  public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
    org.springframework.security.core.userdetails.User principal =
        (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return Optional.of(principal);
  }

  private AuthenticationResponse buildAuthtenticationResponse() {
    AuthenticationResponse response = new AuthenticationResponse();
    response.setMessage("Bad Credentials");
    response.setHttpStatus(HttpStatus.BAD_REQUEST);
    return response;
  }
}
