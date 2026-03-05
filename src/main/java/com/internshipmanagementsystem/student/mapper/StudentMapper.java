package com.internshipmanagementsystem.student.mapper;

import com.internshipmanagementsystem.student.dto.LoginResponse;
import com.internshipmanagementsystem.student.dto.StudentRequest;
import com.internshipmanagementsystem.student.dto.StudentResponse;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.user.model.User;

import com.internshipmanagementsystem.student.dto.ProfileResponse;


public class StudentMapper {

    

   public static Student toEntity(StudentRequest request) {

    return Student.builder()
          
            .firstName(request.getFirstName())
            .middleName(request.getMiddleName())
            .lastName(request.getLastName())
            .dob(request.getDob())
            .gender(request.getGender())
            // .address(request.getAddress())  // if needed
            .build();
}

  
public static StudentResponse toResponse(Student student) {
    User user = student.getUser();

    return StudentResponse.builder()
            .email(user != null ? user.getEmail() : null)
            .mobileNumber(user != null ? user.getMobileNumber() : null)

            .enrollmentNumber(student.getEnrollmentNumber())
            .firstName(student.getFirstName())
            .middleName(student.getMiddleName())
            .lastName(student.getLastName())
            .mobileNumber(
                    student.getUser() != null
                            ? student.getUser().getMobileNumber()
                            : null
            )
            .build();
}
   
    public static LoginResponse toLoginResponse(Student student, String token) {

    return LoginResponse.builder()
            .email(
                    student.getUser() != null
                            ? student.getUser().getEmail()
                            : null
            )
            .id(student.getId())
            .firstName(student.getFirstName())
            .middleName(student.getMiddleName())
            .lastName(student.getLastName())
            .message("Login successful")
            .role(
                    student.getUser() != null
                            ? student.getUser().getRole().name()
                            : null
            )
            .token(token)
            .build();
}


   public static ProfileResponse toProfileResponse(Student student) {

    return ProfileResponse.builder()
            .id(student.getId())
            .enrollmentNumber(student.getEnrollmentNumber())
            .primaryEmail(
                    student.getUser() != null
                            ? student.getUser().getEmail()
                            : null
            )
            .mobileNumber(
                    student.getUser() != null
                            ? student.getUser().getMobileNumber()
                            : null
            )
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
