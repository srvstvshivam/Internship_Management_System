package com.internshipmanagementsystem.student.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.internshipmanagementsystem.student.dto.*;
import com.internshipmanagementsystem.student.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    // Register a new student
    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public ResponseEntity<StudentResponse> register(
            @ModelAttribute @Valid StudentRequest request,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {

        StudentResponse response = studentService.registerStudent(request, profileImage);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = studentService.login(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        ProfileResponse response = studentService.getProfile(email);

        return ResponseEntity.ok(response);
    }
}