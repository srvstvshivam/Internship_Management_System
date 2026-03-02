package com.internshipmanagementsystem.coordinator.model;

import com.internshipmanagementsystem.coordinator.model.enums.DegreeType;
import com.internshipmanagementsystem.coordinator.model.enums.InternshipStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "internships")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String location;
    private String sector;
    private Integer durationInMonths;
    private Double stipendAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate applicationDeadline;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DegreeType> eligibleDegrees;

    @ElementCollection
    private List<String> eligibleBranches;

    @ElementCollection
    private List<String> requiredSkills;

    private Integer minPassoutYear;
    private Integer maxPassoutYear;
    private Double minimumPercentage;
    private Boolean noActiveBacklogsRequired;

    @Column(nullable = false)
    private Integer maxStudents;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InternshipStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinator_id", nullable = false)
    private Coordinator coordinator;

    @OneToMany(mappedBy = "internship",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InternshipApplication> applications;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        // Default state is DRAFT. It must be explicitly published to be OPEN.
        if (this.status == null) {
            this.status = InternshipStatus.DRAFT;
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}


