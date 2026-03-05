package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.LoginRequest;
import com.internshipmanagementsystem.admin.dtos.LoginResponse;
import com.internshipmanagementsystem.admin.mapper.AuthMapper;
import com.internshipmanagementsystem.admin.model.User;
import com.internshipmanagementsystem.admin.repository.UserRepository;
import com.internshipmanagementsystem.admin.service.AuthService;
import com.internshipmanagementsystem.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtService;
    private final UserRepository userRepository; // User fetch karne ke liye zaroori hai

    @Override
    public LoginResponse login(LoginRequest request) {
        // 1. Authenticate
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Fetch User from DB (taaki mapper ko bhej sakein)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));

        // 3. Extract Role for Token
        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");

        // 4. Generate JWT Token
        String token = jwtService.generateToken(request.getEmail(), role);

        // 5. Use Static Mapper to return response
        return AuthMapper.toLoginResponse(user, token);
    }
}