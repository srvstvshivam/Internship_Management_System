package com.internshipmanagementsystem.student.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StudentResponse {

    private Long id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String gender;
}