package com.internshipmanagementsystem.student.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectRequest {

    private String title;
    private String description;
    private String githubUrl;
    private String liveUrl;

    private LocalDate startDate;
    private LocalDate endDate;
}