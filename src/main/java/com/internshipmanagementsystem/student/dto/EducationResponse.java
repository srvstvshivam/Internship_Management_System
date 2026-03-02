package com.internshipmanagementsystem.student.dto;

import com.internshipmanagementsystem.student.model.enums.EducationLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationResponse {

    private Long id;

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
}