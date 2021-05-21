package com.app.statsservice.service;

import com.app.statsservice.dto.RegisterRequest;
import com.app.statsservice.model.*;
import com.app.statsservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private UserRepository userRepository;

  public AuthService(UserRepository userRepository) { this.userRepository = userRepository; }

  public void signup(RegisterRequest registerRequest) {
    User user = new User();
    user.setUserName(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(registerRequest.getPassword());
    userRepository.save(user);
  }
}
