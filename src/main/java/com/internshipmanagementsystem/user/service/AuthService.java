package com.internshipmanagementsystem.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.internshipmanagementsystem.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import com.internshipmanagementsystem.config.JwtUtil;
import com.internshipmanagementsystem.user.dto.LoginRequest;
import com.internshipmanagementsystem.user.model.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(LoginRequest request) {

        User user = userRepository
               .findByEmailOrMobileNumber(request.getEmail(), request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid credentials");
        }

        return jwtUtil.generateToken(user);
    }
}