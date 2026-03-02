package com.internshipmanagementsystem.coordinator.model;

import com.internshipmanagementsystem.coordinator.model.enums.CoordinatorStatus;
import jakarta.persistence.*;
import lombok.*;
import com.internshipmanagementsystem.coordinator.model.enums.Role;

import com.internshipmanagementsystem.coordinator.model.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "coordinators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coordinator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    
    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    private String designation;
    private Integer totalWorkExperienceInYears;


    private String profileImageUrl;

   
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CoordinatorStatus status = CoordinatorStatus.ACTIVE;

 
    @OneToMany(mappedBy = "coordinator", fetch = FetchType.LAZY)
    private List<Internship> internships;

  
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}