package com.internshipmanagementsystem.admin.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class AdminJwtUtil {

    // Use a secure random key
    private final Key SECRET_KEY = Keys.hmacShaKeyFor("wjwerhwehfwfnjfjefnwjwerhwehfwfnjfjefn".getBytes()); 
    private final long EXPIRATION_MS = 5 * 60 * 1000; // 5 minutes

    // Generate JWT token
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)           // email stored in subject
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    
    public String extractEmail(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null; 
        }
    }

    // Optional: check if token is expired
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true; 
        }
    }
}
