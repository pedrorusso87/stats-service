package com.app.statsservice.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {

  private KeyStore keyStore;

  @PostConstruct
  public void init() {
    try {
      keyStore = KeyStore.getInstance("JKS");
      InputStream resourceAsStream = getClass().getResourceAsStream("/statsService.jks");
      keyStore.load(resourceAsStream, "password".toCharArray());
    } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
      e.printStackTrace();
    }
  }

  public String generateToken(Authentication authentication) {
    User principal = (User) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(principal.getUsername())
        .signWith(getPrivateKey())
        .compact();
  }

  private PrivateKey getPrivateKey() {
    try {
      return (PrivateKey) keyStore.getKey("statsservice", "password".toCharArray());
    } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
      throw new RuntimeException(e.getMessage());}
  }

  public boolean validateToken(String jwtToken) {
    Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwtToken);
    return true;
  }

  private PublicKey getPublicKey() {
    try {
      return keyStore.getCertificate("stats").getPublicKey();
    } catch (KeyStoreException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public String getUsernameFromJWT(String jwtToken) {
    Claims claims = Jwts.parser().setSigningKey(getPublicKey())
        .parseClaimsJws(jwtToken)
        .getBody();

    return claims.getSubject();
  }
}
