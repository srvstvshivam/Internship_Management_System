package com.internshipmanagementsystem.mentor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompletionReportRequest {
    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotBlank(message = "Report details are required")
    private String reportDetails;
}