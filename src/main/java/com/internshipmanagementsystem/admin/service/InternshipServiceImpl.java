package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.InternshipDTO;
import com.internshipmanagementsystem.admin.mapper.InternshipMapper;
import com.internshipmanagementsystem.admin.model.Internship;
import com.internshipmanagementsystem.admin.repository.InternshipRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepo;

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('POST_INTERNSHIP')")
    public InternshipDTO create(InternshipDTO internshipDTO) {

        Internship internship = InternshipMapper.toEntity(internshipDTO);
        Internship savedInternship = internshipRepo.save(internship);

        return InternshipMapper.toDTO(savedInternship);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public InternshipDTO update(Long id, InternshipDTO internshipDTO) {

        Internship existing = internshipRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Internship not found with id: " + id));

        existing.setTitle(internshipDTO.getTitle());
        existing.setDescription(internshipDTO.getDescription());
        existing.setCompanyName(internshipDTO.getCompanyName());
        existing.setLocation(internshipDTO.getLocation());
        existing.setStartDate(internshipDTO.getStartDate());
        existing.setEndDate(internshipDTO.getEndDate());

        Internship updatedInternship = internshipRepo.save(existing);

        return InternshipMapper.toDTO(updatedInternship);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {

        if (!internshipRepo.existsById(id)) {
            throw new EntityNotFoundException(
                    "Cannot delete: Internship not found with id: " + id
            );
        }

        internshipRepo.deleteById(id);
    }
}