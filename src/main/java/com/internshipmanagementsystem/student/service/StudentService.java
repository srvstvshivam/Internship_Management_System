package com.internshipmanagementsystem.student.service;



import com.internshipmanagementsystem.student.dto.LoginRequest;
import com.internshipmanagementsystem.student.dto.LoginResponse;
import com.internshipmanagementsystem.student.dto.ProfileResponse;
import com.internshipmanagementsystem.student.dto.StudentRequest;
import com.internshipmanagementsystem.student.dto.StudentResponse;

public interface StudentService {

    StudentResponse registerStudent(StudentRequest request);

    LoginResponse login(LoginRequest request);

    ProfileResponse getProfile(String email);
}