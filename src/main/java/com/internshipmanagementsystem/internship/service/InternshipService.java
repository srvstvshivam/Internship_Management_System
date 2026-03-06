package com.internshipmanagementsystem.internship.service;

import com.internshipmanagementsystem.internship.dto.InternshipRequest;
import com.internshipmanagementsystem.internship.dto.InternshipResponse;
import java.util.List;

public interface InternshipService {
    InternshipResponse createInternship(InternshipRequest request, Long coordinatorId);
    InternshipResponse updateInternship(Long id, InternshipRequest request);
    InternshipResponse publishInternship(Long id);
    InternshipResponse closeInternship(Long id);
    void deleteInternship(Long id);
    InternshipResponse getInternshipById(Long id);
    List<InternshipResponse> getAllInternships();
    List<InternshipResponse> getInternshipsByStatus(String status);
    List<InternshipResponse> getInternshipsByCoordinator(Long coordinatorId);
}