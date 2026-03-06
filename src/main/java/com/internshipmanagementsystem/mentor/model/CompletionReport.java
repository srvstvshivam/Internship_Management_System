package com.internshipmanagementsystem.mentor.model;

import com.internshipmanagementsystem.student.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "completion_reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompletionReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @Column(nullable = false, length = 2000)
    private String reportDetails; // Summary of the student's overall performance

    @Builder.Default
    @Column(nullable = false)
    private Boolean submittedToCoordinator = false; // True once sent to Coordinator

    @Builder.Default
    @Column(nullable = false)
    private String coordinatorApprovalStatus = "PENDING"; // "PENDING", "APPROVED", "REJECTED"

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}