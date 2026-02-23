package com.internshipmanagementsystem.student.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.internshipmanagementsystem.student.dto.LoginRequest;
import com.internshipmanagementsystem.student.dto.LoginResponse;
import com.internshipmanagementsystem.student.dto.StudentRequest;
import com.internshipmanagementsystem.student.dto.StudentResponse;
import com.internshipmanagementsystem.student.service.StudentService;
    

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

@PostMapping("/register")
public ResponseEntity<StudentResponse> register(
        @ModelAttribute @Valid StudentRequest request,
        @RequestParam(value = "profileImage", required = false)
        MultipartFile profileImage
) {
    return ResponseEntity.ok(studentService.registerStudent(request, profileImage));
}

    @PostMapping("/login")
public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest request) {

    return ResponseEntity.ok(studentService.login(request));
}
}