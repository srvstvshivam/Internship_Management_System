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
@Column(nullable = false)
private String password;
   

    private String firstName;
    private String middleName;
    private String lastName;

    private LocalDate dob;

   @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profileImageUrl;
    
  @Column(unique = true)
private String enrollmentNumber;

    @Column(unique = true)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Embedded
    private Address address;

 @Builder.Default
@Enumerated(EnumType.STRING)
private StudentStatus status = StudentStatus.ACTIVE;
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
    // Relations
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private StudentProfile profile;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperience> workExperiences;
}