package com.internshipmanagementsystem.coordinator.service;

import com.internshipmanagementsystem.coordinator.dto.CoordinatorLoginRequest;
import com.internshipmanagementsystem.coordinator.dto.CoordinatorLoginResponse;
import com.internshipmanagementsystem.coordinator.model.Coordinator;
import com.internshipmanagementsystem.coordinator.model.enums.CoordinatorStatus;
import com.internshipmanagementsystem.coordinator.repository.CoordinatorRepository;
import com.internshipmanagementsystem.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class CoordinatorServiceImpl implements CoordinatorService {

    private final CoordinatorRepository coordinatorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public CoordinatorLoginResponse login(CoordinatorLoginRequest request) {

        Coordinator coordinator = coordinatorRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), coordinator.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password");
        }

        if (coordinator.getStatus() != CoordinatorStatus.ACTIVE) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Account is not active");
        }

String token = jwtUtil.generateToken(
        coordinator.getEmail(),
        coordinator.getRole().name()   // convert enum to String
);

return CoordinatorLoginResponse.builder()
        .id(coordinator.getId())
        .firstName(coordinator.getFirstName())
        .middleName(coordinator.getMiddleName())
        .lastName(coordinator.getLastName())
        .email(coordinator.getEmail())
        .role(coordinator.getRole().name())   // convert enum to String
        .token(token)
        .message("Login successful")
        .build();
}
}