package com.internshipmanagementsystem.config;


import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {

    private final String SECRET = "MTY0ODg0NDM4NjY3OTQyOTQ2ODc1NjMxNDYyMzQ1Njc4OTA3NjU0MzIxMDk4NzY1";
    private final long EXPIRATION = 1000*60*60;
    

    //Create a proper HMAC SHA key from these bytes.
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    //Generate Token with role
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Email
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    //Extract Role
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    //Extract All Claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build() //Builds the parser object.
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate Token
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}