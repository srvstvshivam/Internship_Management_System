package com.internshipmanagementsystem.student.model;

import com.internshipmanagementsystem.mentor.model.Mentor;
import com.internshipmanagementsystem.student.model.enums.Gender;
import com.internshipmanagementsystem.student.model.enums.Role;
import com.internshipmanagementsystem.student.model.enums.StudentStatus;
import com.internshipmanagementsystem.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String enrollmentNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    @Column(unique = true)
    private String mobileNumber;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Address address;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StudentStatus status = StudentStatus.ACTIVE;

    // --- YOUR WORK: The Mentor Relationship ---
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    // --- TEAM'S WORK: New Relationships ---
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Education> educations;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Project> projects;

    @JsonIgnore
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StudentProfile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperience> workExperiences;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt; 

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}