package com.internshipmanagementsystem.application.dto;

import com.internshipmanagementsystem.application.model.ApplicationStatus;
import java.time.LocalDateTime;

public class ApplicationResponse {

    private Long id;
    private Long studentId;
    private Long internshipId;
    private String internshipTitle;   
    private ApplicationStatus status;
    private String coverLetter;
    private String coordinatorRemarks;
    private Long reviewedByCoordinatorId;
    private LocalDateTime appliedAt;
    private LocalDateTime reviewedAt;
    private LocalDateTime updatedAt;

   
    public ApplicationResponse(Long id, Long studentId, Long internshipId,
                                String internshipTitle, ApplicationStatus status,
                                String coverLetter, String coordinatorRemarks,
                                Long reviewedByCoordinatorId, LocalDateTime appliedAt,
                                LocalDateTime reviewedAt, LocalDateTime updatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.internshipId = internshipId;
        this.internshipTitle = internshipTitle;
        this.status = status;
        this.coverLetter = coverLetter;
        this.coordinatorRemarks = coordinatorRemarks;
        this.reviewedByCoordinatorId = reviewedByCoordinatorId;
        this.appliedAt = appliedAt;
        this.reviewedAt = reviewedAt;
        this.updatedAt = updatedAt;
    }

    
    public Long getId() { return id; }
    public Long getStudentId() { return studentId; }
    public Long getInternshipId() { return internshipId; }
    public String getInternshipTitle() { return internshipTitle; }
    public ApplicationStatus getStatus() { return status; }
    public String getCoverLetter() { return coverLetter; }
    public String getCoordinatorRemarks() { return coordinatorRemarks; }
    public Long getReviewedByCoordinatorId() { return reviewedByCoordinatorId; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}