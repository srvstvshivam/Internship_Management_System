package com.internshipmanagementsystem.student.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.internshipmanagementsystem.student.dto.*;
import com.internshipmanagementsystem.student.service.StudentService;


@RestController
@RequestMapping("/api/students/auth")
@RequiredArgsConstructor
@CrossOrigin
public class StudentAuthController {

    private final StudentService studentService;

    // Register a new student
   @PostMapping("/register")
public ResponseEntity<StudentResponse> register(
        @RequestBody @Valid StudentRequest request) {

    StudentResponse response = studentService.registerStudent(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}

    // reset and forgot password will be handled by a common controller for all user 
    // types (student, mentor, coordinator) in the notification module 
    // since the logic is mostly the same and only the user type differs          
}