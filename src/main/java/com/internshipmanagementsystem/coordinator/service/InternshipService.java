package com.internshipmanagementsystem.coordinator.service;

import com.internshipmanagementsystem.coordinator.dto.InternshipRequest;
import com.internshipmanagementsystem.coordinator.dto.InternshipResponse;

import java.util.List;

public interface InternshipService {

    InternshipResponse createInternship(String coordinatorEmail, InternshipRequest request);

    InternshipResponse updateInternship(String coordinatorEmail, Long internshipId, InternshipRequest request);

    void deleteInternship(String coordinatorEmail, Long internshipId);

    InternshipResponse getInternship(Long internshipId);

    List<InternshipResponse> getMyInternships(String coordinatorEmail);
    InternshipResponse publishInternship(String email, Long id);
}