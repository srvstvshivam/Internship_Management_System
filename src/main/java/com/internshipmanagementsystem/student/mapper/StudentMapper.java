package com.internshipmanagementsystem.student.mapper;

import com.internshipmanagementsystem.student.dto.LoginResponse;
import com.internshipmanagementsystem.student.dto.StudentRequest;
import com.internshipmanagementsystem.student.dto.StudentResponse;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.model.enums.Role;
import com.internshipmanagementsystem.student.dto.ProfileResponse;


public class StudentMapper {

    
public static Student toEntity(StudentRequest request, String encodedPassword) {

    return Student.builder()
            .email(request.getEmail())
            .password(encodedPassword)
            .firstName(request.getFirstName())
            .middleName(request.getMiddleName())
            .lastName(request.getLastName())
            .dob(request.getDob())
            .gender(request.getGender())
            .mobileNumber(request.getMobileNumber())
            .role(Role.STUDENT)
            .build();
}

  
public static StudentResponse toResponse(Student student) {

    return StudentResponse.builder()
            .enrollmentNumber(student.getEnrollmentNumber()) 
            .email(student.getEmail())
            .firstName(student.getFirstName())
            .middleName(student.getMiddleName())
            .lastName(student.getLastName())
            .mobileNumber(student.getMobileNumber())
            .build();
}
   
    public static LoginResponse toLoginResponse(Student student, String token) {
        return LoginResponse.builder()
                .email(student.getEmail())
                .id(student.getId())
                .firstName(student.getFirstName())
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())
                
                .message("Login successful")
                .role(student.getRole().name())
                .token(token)
                .build();
    }
   public static ProfileResponse toProfileResponse(Student student) {

    return ProfileResponse.builder()
            .id(student.getId())
            .enrollmentNumber(student.getEnrollmentNumber())
            .primaryEmail(student.getEmail())
            .mobileNumber(student.getMobileNumber())

            .firstName(student.getFirstName())
            .middleName(student.getMiddleName())
            .lastName(student.getLastName())
            .dob(student.getDob())
            .gender(student.getGender() != null ? student.getGender().name() : null)

            .profileImageUrl(student.getProfileImageUrl())
            .status(student.getStatus() != null ? student.getStatus().name() : null)

            .address(student.getAddress())
            .build();
}

}
