package com.app.statsservice.service;

import com.app.statsservice.model.User;
import com.app.statsservice.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username).orElseThrow(() ->
        new UsernameNotFoundException("No user found with username" + username));
    return new org.springframework.security.core.userdetails.User(user.getUserName(),
        user.getPassword(),
        true,
        true,
        true,
        true,
        getAuthorities("ROLE_USER"));

  }

  private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
    return Collections.singletonList(new SimpleGrantedAuthority(role_user));
  }
}
