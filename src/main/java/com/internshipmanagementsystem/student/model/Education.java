package com.internshipmanagementsystem.student.model;

import com.internshipmanagementsystem.student.model.enums.EducationLevel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "educations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;
   
    private String institutionName;
    private String educationType;

    private String boardOrUniversity;

    private String specialization; 
  
    private Double percentageOrCgpa;

    private String gradingType; 
    
    private String marksheetUrl;

    private Integer startYear;
    private Integer endYear;
    @Column(nullable = false)
    private Boolean currentlyPursuing;
    private Integer currentSemester;

 @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "student_id", nullable = false)
    private Student student;
}