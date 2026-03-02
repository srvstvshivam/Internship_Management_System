

package com.internshipmanagementsystem.coordinator.model;

import com.internshipmanagementsystem.student.model.Student;

import com.internshipmanagementsystem.coordinator.model.enums.ApplicationStatus;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"student_id", "internship_id"})
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // RELATIONS
    // =========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_id", nullable = false)
    private Internship internship;

    // =========================
    // APPLICATION STATUS
    // =========================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    // =========================
    // TRACKING
    // =========================

    private LocalDateTime appliedAt;

    private LocalDateTime updatedAt;

    private String coordinatorRemarks;

    // =========================
    // AUTO TIMESTAMP
    // =========================

    @PrePersist
    public void onApply() {
        this.appliedAt = LocalDateTime.now();
        this.status = ApplicationStatus.APPLIED;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}