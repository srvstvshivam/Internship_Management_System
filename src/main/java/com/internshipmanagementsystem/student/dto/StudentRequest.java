package com.internshipmanagementsystem.student.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    private LocalDate dob;

    private String gender;
}