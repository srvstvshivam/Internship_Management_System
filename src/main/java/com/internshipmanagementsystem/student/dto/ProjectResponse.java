package com.internshipmanagementsystem.student.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
public class ProjectResponse {

    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private String githubUrl;
    private String liveUrl;

    private LocalDate startDate;
    private LocalDate endDate;
}