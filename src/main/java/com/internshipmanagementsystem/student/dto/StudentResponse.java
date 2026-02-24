package com.internshipmanagementsystem.student.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import com.internshipmanagementsystem.student.model.enums.Gender;

@Data
@Builder
public class StudentResponse {

    private Long id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
    private String profileImageUrl;
}