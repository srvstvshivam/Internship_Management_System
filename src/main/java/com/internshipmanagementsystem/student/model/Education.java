package com.internshipmanagementsystem.student.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "educations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String educationLevel;

    private String institutionName;
    private String programName;

    private Double percentageOrCgpa;

    private String gradingType;

    private Integer startYear;
    private Integer endYear;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}