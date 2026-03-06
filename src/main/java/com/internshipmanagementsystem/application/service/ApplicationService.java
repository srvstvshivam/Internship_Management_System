package com.internshipmanagementsystem.application.service;

import com.internshipmanagementsystem.application.dto.ApplicationResponse;
import com.internshipmanagementsystem.application.dto.ApplicationStatusUpdateRequest;

import java.util.List;

public interface ApplicationService {

    
    List<ApplicationResponse> getAllApplications();

    List<ApplicationResponse> getApplicationsByInternship(Long internshipId);

   
    List<ApplicationResponse> getApplicationsByStatus(String status);

    
    List<ApplicationResponse> getApplicationsByInternshipAndStatus(Long internshipId, String status);

    
    ApplicationResponse getApplicationById(Long id);

   
    ApplicationResponse updateApplicationStatus(Long id, ApplicationStatusUpdateRequest request);

   
    ApplicationResponse selectApplication(Long id, Long coordinatorId, String remarks);

   
    ApplicationResponse rejectApplication(Long id, Long coordinatorId, String remarks);
}