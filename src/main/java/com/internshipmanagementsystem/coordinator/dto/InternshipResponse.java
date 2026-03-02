package com.internshipmanagementsystem.coordinator.dto;

import com.internshipmanagementsystem.coordinator.model.enums.DegreeType;
import com.internshipmanagementsystem.coordinator.model.enums.InternshipStatus;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipResponse {

    private Long id;

    // Basic Information
    private String title;
    private String companyName;
    private String description;
    private String location;
    private String sector;

    // Internship Details
    private Integer durationInMonths;
    private Double stipendAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate applicationDeadline;
    private LocalDate createdAt;

    // Eligibility
    private List<DegreeType> eligibleDegrees;
    private List<String> eligibleBranches;
    private List<String> requiredSkills;

    private Integer minPassoutYear;
    private Integer maxPassoutYear;
    private Double minimumPercentage;
    private Boolean noActiveBacklogsRequired;

    // Control
    private Integer maxStudents;
    private InternshipStatus status;
}