package com.fatima.getwayservice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.Key;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

  @Value("${getwayservice.app.jwtSecret}")
  private String jwtSecret;

  @Value("${getwayservice.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  private Key key() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }

  public boolean validateJwtToken(String authToken) {
    try {
    System.out.println("hiiii"+ Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken).getBody());
    Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken).getBody();

      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

      return false;
  }
}