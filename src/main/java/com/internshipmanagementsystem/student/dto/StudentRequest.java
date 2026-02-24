package com.internshipmanagementsystem.student.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import com.internshipmanagementsystem.student.model.enums.Gender;

import java.time.LocalDate;

@Data
public class StudentRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private LocalDate dob;

    @NotNull(message = "Gender is required")
    private Gender gender;
}