package com.internshipmanagementsystem.student.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import com.internshipmanagementsystem.student.model.enums.Gender;

import java.time.LocalDate;

@Data
public class StudentRequest {

    @NotBlank
    @Email
    private String email;

    @Pattern(
    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
    message = "Password must contain 8 characters, 1 uppercase, 1 lowercase, 1 digit and 1 special character")
    @NotBlank
    private String password;
    

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    private LocalDate dob;

    @NotNull
    private Gender gender;
}