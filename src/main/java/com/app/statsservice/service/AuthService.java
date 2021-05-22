package com.app.statsservice.service;

import com.app.statsservice.dto.LoginRequest;
import com.app.statsservice.dto.RegisterRequest;
import com.app.statsservice.model.*;
import com.app.statsservice.repository.UserRepository;
import com.app.statsservice.security.JWTProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private JWTProvider jwtProvider;

  public AuthService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder,
                     AuthenticationManager authenticationManager,
                     JWTProvider jwtProvider) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtProvider = jwtProvider;
  }

  public void signup(RegisterRequest registerRequest) {
    User user = new User();
    user.setUserName(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(encodePassword(registerRequest.getPassword()));
    userRepository.save(user);
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  public String login(LoginRequest loginRequest) {
    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginRequest.getPassword(),
        loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authenticate);
    return jwtProvider.generateToken(authenticate);
  }
}
