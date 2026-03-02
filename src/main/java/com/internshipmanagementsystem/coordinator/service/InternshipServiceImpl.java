package com.internshipmanagementsystem.coordinator.service;

import com.internshipmanagementsystem.coordinator.dto.*;
import com.internshipmanagementsystem.coordinator.mapper.InternshipMapper;
import com.internshipmanagementsystem.coordinator.model.*;
import com.internshipmanagementsystem.coordinator.model.enums.InternshipStatus;
import com.internshipmanagementsystem.coordinator.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final CoordinatorRepository coordinatorRepository;

    @Override
    public InternshipResponse createInternship(String email, InternshipRequest request) {
        Coordinator coordinator = getCoordinator(email);
        Internship internship = InternshipMapper.toEntity(request, coordinator);
        internship.setStatus(InternshipStatus.DRAFT); // Always start as Draft
        return InternshipMapper.toResponse(internshipRepository.save(internship));
    }

    @Override
    public InternshipResponse updateInternship(String email, Long id, InternshipRequest request) {
        Internship internship = getInternshipAndCheckOwnership(id, email);
        
        // BUSINESS RULE: Cannot edit if already CLOSED or COMPLETED
        if (internship.getStatus() == InternshipStatus.COMPLETED || internship.getStatus() == InternshipStatus.CLOSED) {
            throw new RuntimeException("Cannot update an internship in " + internship.getStatus() + " state");
        }

        InternshipMapper.updateEntity(internship, request);
        return InternshipMapper.toResponse(internshipRepository.save(internship));
    }

    @Override
    public void deleteInternship(String email, Long id) {
        Internship internship = getInternshipAndCheckOwnership(id, email);
        
        // BUSINESS RULE: Only DRAFT internships can be deleted to maintain data integrity
        if (internship.getStatus() != InternshipStatus.DRAFT) {
            throw new RuntimeException("Only DRAFT internships can be deleted. Consider CANCELLED status instead.");
        }
        internshipRepository.delete(internship);
    }

    // NEW WORKFLOW METHOD: PUBLISH
    public InternshipResponse publishInternship(String email, Long id) {
        Internship internship = getInternshipAndCheckOwnership(id, email);
        if (internship.getStatus() != InternshipStatus.DRAFT) {
            throw new RuntimeException("Only DRAFT internships can be published");
        }
        internship.setStatus(InternshipStatus.OPEN);
        internship.setPublishedAt(LocalDateTime.now());
        return InternshipMapper.toResponse(internshipRepository.save(internship));
    }

    @Override
    public InternshipResponse getInternship(Long id) {
        return internshipRepository.findById(id)
                .map(InternshipMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Internship not found"));
    }

    @Override
    public List<InternshipResponse> getMyInternships(String email) {
        return internshipRepository.findByCoordinatorEmail(email)
                .stream().map(InternshipMapper::toResponse).toList();
    }

    // Helper: Centralized Ownership Check
    private Internship getInternshipAndCheckOwnership(Long id, String email) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found"));
        if (!internship.getCoordinator().getEmail().equals(email)) {
            throw new RuntimeException("Access Denied: You do not own this internship");
        }
        return internship;
    }

    private Coordinator getCoordinator(String email) {
        return coordinatorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Coordinator not found"));
    }
}