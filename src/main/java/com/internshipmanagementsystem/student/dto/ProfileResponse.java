package com.internshipmanagementsystem.student.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import com.internshipmanagementsystem.student.model.Address;
    
@Data
@Builder
public class ProfileResponse {
    private Long id;
        private String enrollmentNumber;
private String primaryEmail;
private String mobileNumber;
private String status;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String profileImageUrl;
    private Address address;  
}
