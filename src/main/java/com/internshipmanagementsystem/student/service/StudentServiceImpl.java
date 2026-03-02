package com.internshipmanagementsystem.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.internshipmanagementsystem.config.CloudinaryService;
import com.internshipmanagementsystem.config.JwtUtil;
import com.internshipmanagementsystem.notification.registration.StudentRegistrationEmail;
import com.internshipmanagementsystem.student.dto.*;
import com.internshipmanagementsystem.student.mapper.StudentMapper;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.repository.StudentRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
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

@Transactional
@Override
public StudentResponse registerStudent(StudentRequest request) {

    try {

        // Encode password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Student student = StudentMapper.toEntity(request, encodedPassword);

        Student saved = studentRepository.save(student);

        String year = String.valueOf(LocalDate.now().getYear());
        String enrollmentNumber = "STU" + year + String.format("%04d", saved.getId());

        saved.setEnrollmentNumber(enrollmentNumber);
        saved = studentRepository.save(saved);

        StudentResponse response = StudentMapper.toResponse(saved);

        // Email should NOT break registration
        try {
            StudentRegistrationEmail email = new StudentRegistrationEmail(response);
            emailService.sendEmail(
                    email.getEmail(),
                    email.getSubject(),
                    email.buildBody()
            );
        } catch (Exception e) {
            System.out.println("Email sending failed: " + e.getMessage());
        }

        return response;

    } catch (DataIntegrityViolationException ex) {

        String message = ex.getMostSpecificCause().getMessage();

        if (message.contains("email")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email already registered"
            );
        }

        if (message.contains("mobile_number")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Mobile number already registered"
            );
        }

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Duplicate data found"
        );

    } catch (Exception ex) {

        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Registration failed. Please try again."
        );
    }
}
    @Override
    public LoginResponse login(LoginRequest request) {

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalidpassword");
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

    @Override
    public ProfileResponse updateProfile(String email,UpdateProfileRequest request,MultipartFile file) {

        Student student = studentRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        if (request.getFirstName() != null)
            student.setFirstName(request.getFirstName());

        if (request.getMiddleName() != null)
            student.setMiddleName(request.getMiddleName());

        if (request.getLastName() != null)
            student.setLastName(request.getLastName());

        if (request.getDob() != null)
            student.setDob(request.getDob());

        if (request.getGender() != null)
            student.setGender(request.getGender());

        if (request.getMobileNumber() != null)
            student.setMobileNumber(request.getMobileNumber());

        if (request.getAddress() != null)
            student.setAddress(request.getAddress());

        if (file != null && !file.isEmpty()) {

            String imageUrl = cloudinaryService
                    .uploadFile(file, "student/profile");

            student.setProfileImageUrl(imageUrl);
        }

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.toProfileResponse(updatedStudent);
    }

}
