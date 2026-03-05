package com.internshipmanagementsystem.admin.mapper;

import com.internshipmanagementsystem.admin.dtos.CoordinatorRegisterDTO;
import com.internshipmanagementsystem.admin.dtos.CoordinatorUpdateDTO;
import com.internshipmanagementsystem.admin.dtos.CoordinatorResponseDTO;
import com.internshipmanagementsystem.admin.model.Coordinator;
import org.springframework.stereotype.Component;

@Component
public class CoordinatorMapper {

    // Map Register DTO → Entity
    public Coordinator toModel(CoordinatorRegisterDTO dto) {
        return Coordinator.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .department(dto.getDepartment())
                .password(dto.getPassword()) // encode in service layer
                .build();
    }

    // Map Entity → Response DTO
    public CoordinatorResponseDTO toDTO(Coordinator entity) {
        return CoordinatorResponseDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .department(entity.getDepartment())
                .build();
    }

    // Update Entity from Update DTO
    public void updateEntity(Coordinator entity, CoordinatorUpdateDTO dto) {
        if (dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) entity.setLastName(dto.getLastName());
        if (dto.getPhoneNumber() != null) entity.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getDepartment() != null) entity.setDepartment(dto.getDepartment());
    }
}