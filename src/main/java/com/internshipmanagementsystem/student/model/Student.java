package com.internshipmanagementsystem.student.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.internshipmanagementsystem.student.model.enums.Role;
import com.internshipmanagementsystem.student.model.enums.StudentStatus;
import com.internshipmanagementsystem.student.model.enums.Gender;

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

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String firstName;
    private String middleName;
    private String lastName;

    private LocalDate dob;

    @Embedded
    private Gender gender;

    private String profileImageUrl;

    private Boolean profileCompleted = false;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    // Relations
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfile profile;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperience> workExperiences;
}