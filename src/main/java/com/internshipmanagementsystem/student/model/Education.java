package com.internshipmanagementsystem.student.model;

import com.internshipmanagementsystem.student.model.enums.EducationLevel;

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

    private Boolean currentlyPursuing;
    private Integer currentSemester;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}