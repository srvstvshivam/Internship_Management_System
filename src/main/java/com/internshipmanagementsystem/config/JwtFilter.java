package com.internshipmanagementsystem.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.List;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter { 
     @Autowired
    private JwtUtil jwtUtil;
@Override
protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    System.out.println("Authorization Header: " + authHeader);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        System.out.println("No JWT token found in request");
        filterChain.doFilter(request, response);
        return;
    }

    String token = authHeader.substring(7);

    System.out.println("JWT TOKEN: " + token);

    if (jwtUtil.validateToken(token)) {

        String email = jwtUtil.extractEmail(token);
        String role = jwtUtil.extractRole(token);

        System.out.println("JWT Email: " + email);
        System.out.println("JWT Role: " + role);

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + role));

        System.out.println("Authorities assigned: " + authorities);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        authorities
                );

        SecurityContextHolder.getContext().setAuthentication(auth);

        System.out.println("Authentication set in SecurityContext");
    } else {
        System.out.println("JWT token is INVALID");
    }

    filterChain.doFilter(request, response);
}
}