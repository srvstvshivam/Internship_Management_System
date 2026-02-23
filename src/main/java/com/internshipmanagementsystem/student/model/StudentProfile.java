package com.internshipmanagementsystem.student.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "student_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumeUrl;

    private String skills; 

    private String linkedinUrl;
    private String githubUrl;
    private String portfolioUrl;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
}