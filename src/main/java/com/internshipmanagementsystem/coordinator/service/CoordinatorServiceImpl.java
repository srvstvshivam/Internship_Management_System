package com.internshipmanagementsystem.coordinator.service;

import com.internshipmanagementsystem.config.JwtUtil;
import com.internshipmanagementsystem.coordinator.dto.CoordinatorLoginRequest;
import com.internshipmanagementsystem.coordinator.dto.CoordinatorLoginResponse;
import com.internshipmanagementsystem.coordinator.model.Coordinator;
import com.internshipmanagementsystem.coordinator.repository.CoordinatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorServiceImpl implements CoordinatorService {

    @Autowired
    private CoordinatorRepository coordinatorRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public CoordinatorLoginResponse login(CoordinatorLoginRequest request) {

        Coordinator coordinator = coordinatorRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Coordinator not found"));

        if (!passwordEncoder.matches(request.getPassword(), coordinator.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(coordinator.getEmail(), coordinator.getRole());

        return new CoordinatorLoginResponse(token, coordinator.getEmail(), coordinator.getRole());
    }
}