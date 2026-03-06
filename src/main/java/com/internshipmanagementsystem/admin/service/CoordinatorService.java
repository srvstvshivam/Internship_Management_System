package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.CoordinatorRegisterDTO;
import com.internshipmanagementsystem.admin.dtos.CoordinatorResponseDTO;
import com.internshipmanagementsystem.admin.model.Coordinator;
import com.internshipmanagementsystem.admin.repository.CoordinatorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoordinatorService {

    private final CoordinatorRepository repo;
    private final PasswordEncoder encoder;

    // CREATE COORDINATOR
    public CoordinatorResponseDTO createCoordinator(CoordinatorRegisterDTO dto){

        if(repo.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("Coordinator with this email already exists");
        }

        Coordinator coordinator = Coordinator.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .department(dto.getDepartment())
                .password(encoder.encode(dto.getPassword()))
                .active(true)
                .role("ROLE_COORDINATOR")
                .build();

        Coordinator saved = repo.save(coordinator);

        return mapToDTO(saved);
    }

    // GET ALL COORDINATORS
    public List<CoordinatorResponseDTO> getAllCoordinators(){

        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // TOGGLE ACTIVE STATUS
    public CoordinatorResponseDTO toggleActive(Long id){

        Coordinator coordinator = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinator not found with id: " + id));

        coordinator.setActive(!coordinator.isActive());

        Coordinator updated = repo.save(coordinator);

        return mapToDTO(updated);
    }

    // ASSIGN ROLE
    public CoordinatorResponseDTO assignRole(Long id, String role){

        Coordinator coordinator = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinator not found with id: " + id));

        if(!role.startsWith("ROLE_")){
            role = "ROLE_" + role.toUpperCase();
        }

        coordinator.setRole(role);

        Coordinator updated = repo.save(coordinator);

        return mapToDTO(updated);
    }

    // DELETE COORDINATOR
    public void deleteCoordinator(Long id){

        Coordinator coordinator = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinator not found with id: " + id));

        repo.delete(coordinator);
    }

    // FIND BY EMAIL
    public Coordinator findByEmail(String email){

        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Coordinator not found with email: " + email));
    }

    // ENTITY → DTO
    private CoordinatorResponseDTO mapToDTO(Coordinator coordinator){

        return CoordinatorResponseDTO.builder()
                .id(coordinator.getId())
                .firstName(coordinator.getFirstName())
                .lastName(coordinator.getLastName())
                .email(coordinator.getEmail())
                .phoneNumber(coordinator.getPhoneNumber())
                .department(coordinator.getDepartment())
                .active(coordinator.isActive())
                .role(coordinator.getRole())
                .build();
    }
}