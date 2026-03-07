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

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Map<String, Object> login(LoginRequest request) {

        User user = userRepository
                .findByLoginIdOrEmailOrMobileNumber(
                        request.getIdentifier(),
                        request.getIdentifier(),
                        request.getIdentifier()
                )
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid credentials"
                ));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid credentials"
            );
        }

        String token = jwtUtil.generateToken(user);

        return Map.of(
                "token", token,
                "role", user.getRole(),
                "loginId", user.getLoginId()
        );
    }
}