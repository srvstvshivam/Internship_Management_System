package com.internshipmanagementsystem.admin.service;


import com.internshipmanagementsystem.admin.dtos.*;
import com.internshipmanagementsystem.admin.model.User;
import com.internshipmanagementsystem.admin.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.internshipmanagementsystem.config.JwtUtil;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Hardcoded Admin
    private final String email = "admin@gmail.com";
    private final String password = "admin123";

    public String login(LoginRequest request) {

        if (request.getEmail().equals(email)
                && request.getPassword().equals(password)) {

            return jwtUtil.generateToken(email, "ADMIN");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    public void changePassword(String email, ChangePasswordRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setFirstLogin(false);

        userRepository.save(user);
    }
}