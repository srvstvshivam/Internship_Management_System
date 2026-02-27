package com.internshipmanagementsystem.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.internshipmanagementsystem.config.JwtUtil;
import com.internshipmanagementsystem.notification.registration.StudentRegistrationEmail;
import com.internshipmanagementsystem.student.dto.*;
import com.internshipmanagementsystem.student.mapper.StudentMapper;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.internshipmanagementsystem.notification.EmailService;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CloudinaryService cloudinaryService;
    private final EmailService emailService;
@Override
public StudentResponse registerStudent(StudentRequest request) {

    if (studentRepository.findByEmail(request.getEmail()).isPresent()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
    }

    String encodedPassword = passwordEncoder.encode(request.getPassword());

    Student student = StudentMapper.toEntity(request, encodedPassword);

    Student saved = studentRepository.save(student);

    String year = String.valueOf(LocalDate.now().getYear());
    String enrollmentNumber = "STU" + year + String.format("%04d", saved.getId());

    saved.setEnrollmentNumber(enrollmentNumber);

    studentRepository.save(saved);

    // ✅ Convert to response
    StudentResponse response = StudentMapper.toResponse(saved);

    // ✅ Create email object
    StudentRegistrationEmail email =
            new StudentRegistrationEmail(response);

    // ✅ Send email
    emailService.sendEmail(
            email.getEmail(),
            email.getSubject(),
            email.buildBody()
    );

    return response;
}

    @Override
    public LoginResponse login(LoginRequest request) {

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        String token = jwtUtil.generateToken(student.getEmail(), student.getRole().name());

        return StudentMapper.toLoginResponse(student, token);
    }

    @Override
    public ProfileResponse getProfile(String email) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        return StudentMapper.toProfileResponse(student);
    }
}