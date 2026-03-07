package com.internshipmanagementsystem.student.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ProfileResponse {

    private Long id;
    private String enrollmentNumber;

    private String primaryEmail;
    private String mobileNumber;

    private String firstName;
    private String middleName;
    private String lastName;

    private LocalDate dob;
    private String gender;

    private String profileImageUrl;
    private String status;

    private AddressResponse permanentAddress;
    private AddressResponse correspondenceAddress;
}