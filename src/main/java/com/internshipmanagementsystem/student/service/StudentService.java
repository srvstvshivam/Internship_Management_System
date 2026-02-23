package com.internshipmanagementsystem.student.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.internshipmanagementsystem.student.dto.LoginRequest;
import com.internshipmanagementsystem.student.dto.LoginResponse;    
import com.internshipmanagementsystem.student.dto.StudentRequest;
import com.internshipmanagementsystem.student.dto.StudentResponse;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import com.internshipmanagementsystem.student.model.enums.Role;
import com.internshipmanagementsystem.student.config.JwtUtil;


import java.time.LocalDateTime;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CloudinaryService cloudinaryService;

    public StudentResponse registerStudent(StudentRequest request,
                                           MultipartFile profileImage) {

        if (studentRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        String imageUrl = null;

        try {
            if (profileImage != null && !profileImage.isEmpty()) {
                imageUrl = cloudinaryService.uploadFile(profileImage);
            }

            Student student = Student.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .firstName(request.getFirstName())
                    .middleName(request.getMiddleName())
                    .lastName(request.getLastName())
                    .dob(request.getDob())
                    .gender(request.getGender())
                    .role(Role.STUDENT)
                    .profileImageUrl(imageUrl)   
                    .createdAt(LocalDateTime.now())
                    .build();

            Student saved = studentRepository.save(student);

            return StudentResponse.builder()
                    .id(saved.getId())
                    .email(saved.getEmail())
                    .firstName(saved.getFirstName())
                    .middleName(saved.getMiddleName())
                    .lastName(saved.getLastName())
                    .dob(saved.getDob())
                    .gender(saved.getGender())
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("Image upload failed");
        }
    }


  public LoginResponse login(LoginRequest request) {

    Student student = studentRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

    if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    String token = jwtUtil.generateToken(
            student.getEmail(),
            student.getRole().name()
    );

    return LoginResponse.builder()
            .email(student.getEmail())
            .firstName(student.getFirstName())
            .MiddleName(student.getMiddleName())
            .lastName(student.getLastName())
            .role(student.getRole().name())
            .token(token)
            .build();
}
    
}
