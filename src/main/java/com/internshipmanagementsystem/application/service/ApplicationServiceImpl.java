package com.internshipmanagementsystem.application.service;

import com.internshipmanagementsystem.application.dto.ApplicationResponse;
import com.internshipmanagementsystem.application.dto.ApplicationStatusUpdateRequest;
import com.internshipmanagementsystem.application.model.Application;
import com.internshipmanagementsystem.application.model.ApplicationStatus;
import com.internshipmanagementsystem.application.repository.ApplicationRepository;
import com.internshipmanagementsystem.internship.model.Internship;
import com.internshipmanagementsystem.internship.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InternshipRepository internshipRepository;

   
    @Override
    public List<ApplicationResponse> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

   
    @Override
    public List<ApplicationResponse> getApplicationsByInternship(Long internshipId) {
        return applicationRepository.findByInternshipId(internshipId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

   
    @Override
    public List<ApplicationResponse> getApplicationsByStatus(String status) {
        try {
            ApplicationStatus appStatus = ApplicationStatus.valueOf(status.toUpperCase());
            return applicationRepository.findByStatus(appStatus)
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status +
                    ". Valid: UNDER_REVIEW, SELECTED, REJECTED");
        }
    }

    
    @Override
    public List<ApplicationResponse> getApplicationsByInternshipAndStatus(
            Long internshipId, String status) {
        try {
            ApplicationStatus appStatus = ApplicationStatus.valueOf(status.toUpperCase());
            return applicationRepository
                    .findByInternshipIdAndStatus(internshipId, appStatus)
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }
    }

   
    @Override
    public ApplicationResponse getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
        return mapToResponse(application);
    }

    
    @Override
    public ApplicationResponse updateApplicationStatus(
            Long id, ApplicationStatusUpdateRequest request) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

        try {
            ApplicationStatus newStatus = ApplicationStatus.valueOf(
                    request.getStatus().toUpperCase());
            application.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + request.getStatus());
        }

        application.setCoordinatorRemarks(request.getCoordinatorRemarks());
        application.setReviewedByCoordinatorId(request.getCoordinatorId());
        application.setReviewedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(applicationRepository.save(application));
    }

   
    @Override
    public ApplicationResponse selectApplication(
            Long id, Long coordinatorId, String remarks) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

        
        Internship internship = internshipRepository
                .findById(application.getInternshipId())
                .orElseThrow(() -> new RuntimeException("Internship not found"));

        long selectedCount = applicationRepository.countByInternshipIdAndStatus(
                internship.getId(), ApplicationStatus.SELECTED);

        if (selectedCount >= internship.getMaxStudents()) {
            throw new RuntimeException("Maximum student limit reached for this internship");
        }

        application.setStatus(ApplicationStatus.SELECTED);
        application.setCoordinatorRemarks(remarks);
        application.setReviewedByCoordinatorId(coordinatorId);
        application.setReviewedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());

        
        internship.setEnrolledStudents((int) selectedCount + 1);
        internshipRepository.save(internship);

        return mapToResponse(applicationRepository.save(application));
    }

    
    @Override
    public ApplicationResponse rejectApplication(
            Long id, Long coordinatorId, String remarks) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

       
        if (application.getStatus() == ApplicationStatus.SELECTED) {
            Internship internship = internshipRepository
                    .findById(application.getInternshipId())
                    .orElseThrow(() -> new RuntimeException("Internship not found"));

            int current = internship.getEnrolledStudents();
            internship.setEnrolledStudents(Math.max(0, current - 1));
            internshipRepository.save(internship);
        }

        application.setStatus(ApplicationStatus.REJECTED);
        application.setCoordinatorRemarks(remarks);
        application.setReviewedByCoordinatorId(coordinatorId);
        application.setReviewedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());

        return mapToResponse(applicationRepository.save(application));
    }

    
    private ApplicationResponse mapToResponse(Application a) {
        
        String internshipTitle = internshipRepository.findById(a.getInternshipId())
                .map(i -> i.getTitle())
                .orElse("Unknown Internship");

        return new ApplicationResponse(
                a.getId(), a.getStudentId(), a.getInternshipId(),
                internshipTitle, a.getStatus(), a.getCoverLetter(),
                a.getCoordinatorRemarks(), a.getReviewedByCoordinatorId(),
                a.getAppliedAt(), a.getReviewedAt(), a.getUpdatedAt()
        );
    }
}