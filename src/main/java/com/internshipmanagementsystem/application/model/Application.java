package com.internshipmanagementsystem.application.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    
    @Column(name = "internship_id", nullable = false)
    private Long internshipId;

    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.UNDER_REVIEW;

    
    @Column(name = "cover_letter", columnDefinition = "TEXT")
    private String coverLetter;

    
    @Column(name = "coordinator_remarks", columnDefinition = "TEXT")
    private String coordinatorRemarks;

   
    @Column(name = "reviewed_by_coordinator_id")
    private Long reviewedByCoordinatorId;

    
    @Column(name = "applied_at")
    private LocalDateTime appliedAt;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

   

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getInternshipId() { return internshipId; }
    public void setInternshipId(Long internshipId) { this.internshipId = internshipId; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }

    public String getCoordinatorRemarks() { return coordinatorRemarks; }
    public void setCoordinatorRemarks(String coordinatorRemarks) { this.coordinatorRemarks = coordinatorRemarks; }

    public Long getReviewedByCoordinatorId() { return reviewedByCoordinatorId; }
    public void setReviewedByCoordinatorId(Long reviewedByCoordinatorId) { this.reviewedByCoordinatorId = reviewedByCoordinatorId; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }

    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime reviewedAt) { this.reviewedAt = reviewedAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}