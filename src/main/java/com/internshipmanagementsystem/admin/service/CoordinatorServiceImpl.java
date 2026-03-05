package com.internshipmanagementsystem.admin.service;




import com.internshipmanagementsystem.admin.dtos.*;
import com.internshipmanagementsystem.admin.mapper.CoordinatorMapper;
import com.internshipmanagementsystem.admin.model.Coordinator;
import com.internshipmanagementsystem.admin.repository.CoordinatorRepository;
import com.internshipmanagementsystem.admin.service.CoordinatorService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoordinatorServiceImpl implements CoordinatorService {

    private final CoordinatorRepository repository;
    private final CoordinatorMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CoordinatorResponseDTO registerCoordinator(CoordinatorRegisterDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Coordinator with email already exists");
        }
        Coordinator coordinator = mapper.toEntity(dto);
        coordinator.setPassword(passwordEncoder.encode(dto.getPassword()));
        repository.save(coordinator);
        return mapper.toDTO(coordinator);
    }

    @Override
    public CoordinatorResponseDTO updateCoordinator(Long id, CoordinatorUpdateDTO dto) {
        Coordinator coordinator = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinator not found"));
        mapper.updateEntity(coordinator, dto);
        repository.save(coordinator);
        return mapper.toDTO(coordinator);
    }
}