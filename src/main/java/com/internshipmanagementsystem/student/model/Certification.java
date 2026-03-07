package com.internshipmanagementsystem.student.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "certifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String issuingAuthority;

    private String licenseNumber;

    private String certificationUrl;

    private LocalDate certificationDate;

    private LocalDate expiryDate;

    @Column(length = 1500)
    private String description;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}