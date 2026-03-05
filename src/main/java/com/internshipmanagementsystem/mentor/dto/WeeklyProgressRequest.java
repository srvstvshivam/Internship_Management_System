package com.internshipmanagementsystem.mentor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WeeklyProgressRequest {
    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Week number is required")
    private Integer weekNumber;

    @NotBlank(message = "Task description is required")
    private String taskDescription;

    @NotBlank(message = "Completion status is required")
    private String completionStatus;

    private String remarks;
}