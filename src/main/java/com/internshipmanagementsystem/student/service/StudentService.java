package com.internshipmanagementsystem.student.service;

import org.springframework.web.multipart.MultipartFile;

import com.internshipmanagementsystem.student.dto.LoginRequest;
import com.internshipmanagementsystem.student.dto.LoginResponse;
import com.internshipmanagementsystem.student.dto.ProfileResponse;
import com.internshipmanagementsystem.student.dto.StudentRequest;
import com.internshipmanagementsystem.student.dto.StudentResponse;

public interface StudentService {

    StudentResponse registerStudent(StudentRequest request, MultipartFile profileImage);

    LoginResponse login(LoginRequest request);

    ProfileResponse getProfile(String email);
}