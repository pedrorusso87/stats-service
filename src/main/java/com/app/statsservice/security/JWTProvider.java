package com.app.statsservice.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
public class JWTProvider {

  private Key key;

  @PostConstruct
  public void init() {
    key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
  }

  public String generateToken(Authentication authentication) {
    User principal = (User) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(principal.getUsername())
        .signWith(key)
        .compact();
  }

  public boolean validateToken(String jwtToken) {
    Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);
    return true;
  }

  public String getUsernameFromJWT(String jwtToken) {
    Claims claims = Jwts.parser().setSigningKey(key)
        .parseClaimsJws(jwtToken)
        .getBody();

    return claims.getSubject();
  }
}
