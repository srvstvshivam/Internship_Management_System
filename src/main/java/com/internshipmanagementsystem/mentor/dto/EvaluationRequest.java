package com.internshipmanagementsystem.mentor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EvaluationRequest {
    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotBlank(message = "Grade is required")
    private String grade;

    @NotBlank(message = "Remarks are required")
    private String remarks;

    private Boolean isVisibleToStudent;
}