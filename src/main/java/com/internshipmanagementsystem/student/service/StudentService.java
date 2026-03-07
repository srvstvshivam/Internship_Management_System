package com.internshipmanagementsystem.student.service;



import org.springframework.web.multipart.MultipartFile;

import com.internshipmanagementsystem.student.dto.LoginRequest;
import com.internshipmanagementsystem.student.dto.LoginResponse;
import com.internshipmanagementsystem.student.dto.ProfileImageResponse;
import com.internshipmanagementsystem.student.dto.ProfileResponse;
import com.internshipmanagementsystem.student.dto.StudentRequest;
import com.internshipmanagementsystem.student.dto.StudentResponse;
import com.internshipmanagementsystem.student.dto.UpdateProfileRequest;

public interface StudentService {

    StudentResponse registerStudent(StudentRequest request);

    
    ProfileResponse getProfile(String email);

    ProfileResponse updateProfile(String email,UpdateProfileRequest request);
    ProfileImageResponse uploadProfileImage(String email, MultipartFile file);
}