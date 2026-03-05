package com.internshipmanagementsystem.admin.controller;

import com.internshipmanagementsystem.admin.dtos.LoginRequest;
import com.internshipmanagementsystem.admin.dtos.LoginResponse;

import com.internshipmanagementsystem.config.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    
    private final JwtUtil jwtUtil;

    private static final String EMAIL = "admin@gmail.com";
    private static final String PASSWORD = "admin123";

    //  Admin Login
    @PostMapping("/login")
public ResponseEntity<LoginResponse> adminLogin(@RequestBody LoginRequest login) {

    if (EMAIL.equals(login.getEmail()) &&
        PASSWORD.equals(login.getPassword())) {

        String token = jwtUtil.generateToken(EMAIL, "ADMIN");

        LoginResponse response = LoginResponse.builder()
                .token(token)
                .email(EMAIL)
                .role("ADMIN")
                .build();

        return ResponseEntity.ok(response);
    }

    throw new RuntimeException("Invalid Admin credentials");
}
    
}