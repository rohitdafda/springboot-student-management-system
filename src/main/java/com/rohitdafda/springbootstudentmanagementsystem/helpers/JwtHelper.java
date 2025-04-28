package com.rohitdafda.springbootstudentmanagementsystem.helpers;

import com.rohitdafda.springbootstudentmanagementsystem.exceptions.AccessDeniedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtHelper {

  private static final String SECRET_KEY = "83df92b035be62cc6c120d0d0fb4e797e4c6d9b0f9bd73433f506b204640f17161aebfa00f0816a57df3e5d77b2b86cccc4e20996e377216edacaf7c4e603b3bd3cef05f08f55282a0273c5eef085c7d29ace39bcc44d3e85ce006788e6ec37d1e92abdc1ef34dca8031f8a99302a97e8ea90c76786b68c13943bc990118538481744134f6ae8706177f4ea24740b4f63db0d4fee76843ebafe2867018b9a44e6e604eb48b39a41ca9a2a790300a0b134face24491a2284339f6fc03e51672249bf230c6c8440f250689d82c5224e7bf4f86ee58739c3118bc35a3d450315a55a7bc78cca4adf9250a96f2cb461a4202d26d37ced6b84bb734383ab116aae79f";;
  private static final int MINUTES = 60;

  public static String generateToken(String userIdentifierValue, String role) {
    var now = Instant.now();
    return Jwts.builder()
        .subject(userIdentifierValue)
        .claim("role", role)
        .issuedAt(Date.from(now))
        .expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  public static String extractUsername(String token) {
    return getTokenBody(token).getSubject();
  }

  public static String extractRole(String token) {
    return getTokenBody(token).get("role", String.class);  // Extract the role from the custom claim
  }


  public static Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private static Claims getTokenBody(String token) {
    try {
      return Jwts
          .parser()
          .setSigningKey(SECRET_KEY)
          .build()
          .parseSignedClaims(token)
          .getPayload();
    } catch (SignatureException | ExpiredJwtException e) { // Invalid signature or expired token
      throw new AccessDeniedException("Access denied: " + e.getMessage());
    }
  }

  private static boolean isTokenExpired(String token) {
    Claims claims = getTokenBody(token);
    return claims.getExpiration().before(new Date());
  }
}