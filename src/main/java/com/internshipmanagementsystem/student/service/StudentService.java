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
import com.internshipmanagementsystem.student.service.mapper.StudentMapper;
import com.internshipmanagementsystem.student.config.JwtUtil;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CloudinaryService cloudinaryService;

    // register
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
        } catch (IOException e) {
            throw new RuntimeException("Profile image upload failed");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Student student = StudentMapper.toEntity(
                request,
                encodedPassword,
                imageUrl
        );

        Student saved = studentRepository.save(student);

        return StudentMapper.toResponse(saved);
    }

    //login
 public LoginResponse login(LoginRequest request) {

    Student student = studentRepository.findByEmail(request.getEmail()).orElse(null);

    if (student == null) {
        throw new RuntimeException("Invalid email or password");
    }

    if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
        throw new RuntimeException("Invalid email or password");
    }

    String token = jwtUtil.generateToken(student.getEmail(), student.getRole().name());

    return StudentMapper.toLoginResponse(student, token);
}
}