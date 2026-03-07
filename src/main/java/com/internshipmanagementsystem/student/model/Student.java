package com.internshipmanagementsystem.student.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.internshipmanagementsystem.student.model.enums.StudentStatus;
import com.internshipmanagementsystem.student.model.enums.Gender;
import com.internshipmanagementsystem.user.model.User;

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

    private String firstName;
    private String middleName;
    private String lastName;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profileImageUrl;

    @Column(unique = true)
    private String enrollmentNumber;

  @Embedded
@AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "permanent_street")),
        @AttributeOverride(name = "city", column = @Column(name = "permanent_city")),
        @AttributeOverride(name = "state", column = @Column(name = "permanent_state")),
        @AttributeOverride(name = "country", column = @Column(name = "permanent_country")),
        @AttributeOverride(name = "pincode", column = @Column(name = "permanent_pincode"))
})
private Address permanentAddress;


@Embedded
@AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "correspondence_street")),
        @AttributeOverride(name = "city", column = @Column(name = "correspondence_city")),
        @AttributeOverride(name = "state", column = @Column(name = "correspondence_state")),
        @AttributeOverride(name = "country", column = @Column(name = "correspondence_country")),
        @AttributeOverride(name = "pincode", column = @Column(name = "correspondence_pincode"))
})
private Address correspondenceAddress;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private StudentLinksResume profile;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfileSkillLanguage> skillsLanguages;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certification> certifications;
}
