package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.InternshipDTO;
import com.internshipmanagementsystem.admin.model.Internship;
import com.internshipmanagementsystem.admin.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository repository;

    // Convert Entity -> DTO
    private InternshipDTO mapToDTO(Internship internship) {
        InternshipDTO dto = new InternshipDTO();
        dto.setId(internship.getId());
        dto.setTitle(internship.getTitle());
        dto.setDescription(internship.getDescription());
        dto.setCompanyName(internship.getCompanyName());
        dto.setStartDate(internship.getStartDate());
        dto.setEndDate(internship.getEndDate());
        dto.setLocation(internship.getLocation());
        dto.setStipend(internship.getStipend());
        return dto;
    }

    // Convert DTO -> Entity
    private Internship mapToEntity(InternshipDTO dto) {
        Internship internship = new Internship();
        internship.setId(dto.getId());
        internship.setTitle(dto.getTitle());
        internship.setDescription(dto.getDescription());
        internship.setCompanyName(dto.getCompanyName());
        internship.setStartDate(dto.getStartDate());
        internship.setEndDate(dto.getEndDate());
        internship.setLocation(dto.getLocation());
        internship.setStipend(dto.getStipend());
        return internship;
    }

    // Create Internship
    public InternshipDTO create(InternshipDTO dto) {
        Internship internship = mapToEntity(dto);
        Internship saved = repository.save(internship);
        return mapToDTO(saved);
    }

    // Update Internship
    public InternshipDTO update(Long id, InternshipDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(dto.getTitle());
                    existing.setDescription(dto.getDescription());
                    existing.setCompanyName(dto.getCompanyName());
                    existing.setStartDate(dto.getStartDate());
                    existing.setEndDate(dto.getEndDate());
                    existing.setLocation(dto.getLocation());
                    existing.setStipend(dto.getStipend());
                    return mapToDTO(repository.save(existing));
                }).orElse(null);
    }

    // Delete Internship
    public boolean delete(Long id) {
        return repository.findById(id)
                .map(existing -> {
                    repository.delete(existing);
                    return true;
                }).orElse(false);
    }

    // Get All Internships
    public List<InternshipDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get Internship by ID
    public InternshipDTO getById(Long id) {
        return repository.findById(id).map(this::mapToDTO).orElse(null);
    }
}