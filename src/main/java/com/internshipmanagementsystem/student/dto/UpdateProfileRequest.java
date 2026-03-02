package com.internshipmanagementsystem.student.dto;

import lombok.Data;
import java.time.LocalDate;

import com.internshipmanagementsystem.student.model.Address;
import com.internshipmanagementsystem.student.model.enums.Gender;

@Data
public class UpdateProfileRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
    private String mobileNumber;
    private Address address;
}