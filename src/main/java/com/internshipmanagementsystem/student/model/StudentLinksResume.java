package com.internshipmanagementsystem.student.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_links_resume")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentLinksResume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumeUrl;

    private String linkedinUrl;
    private String githubUrl;
    private String portfolioUrl;

    @Column(length = 1500)
    private String profileSummary;

   @OneToOne
@JoinColumn(name = "student_id", unique = true)
private Student student;
}