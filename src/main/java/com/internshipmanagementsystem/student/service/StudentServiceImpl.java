package com.internshipmanagementsystem.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.internshipmanagementsystem.config.CloudinaryService;
import com.internshipmanagementsystem.config.JwtUtil;
import com.internshipmanagementsystem.notification.registration.StudentRegistrationEmail;
import com.internshipmanagementsystem.notification.EmailService;
import com.internshipmanagementsystem.student.dto.*;
import com.internshipmanagementsystem.student.mapper.StudentMapper;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import com.internshipmanagementsystem.user.model.User;
import com.internshipmanagementsystem.user.model.Enums.UserRole;
import com.internshipmanagementsystem.user.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CloudinaryService cloudinaryService;
    private final EmailService emailService;
@Transactional
@Override
public StudentResponse registerStudent(StudentRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Email already registered"
        );
    }

    if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Mobile number already registered"
        );
    }

    String encodedPassword = passwordEncoder.encode(request.getPassword());

    User user = User.builder()
            .email(request.getEmail())
            .mobileNumber(request.getMobileNumber())
            .password(encodedPassword)
            .role(UserRole.STUDENT)
            .build();

    User savedUser = userRepository.save(user);

    Student student = StudentMapper.toEntity(request);
    student.setUser(savedUser);

    Student savedStudent = studentRepository.save(student);

    String year = String.valueOf(LocalDate.now().getYear());
    String enrollmentNumber = "STU" + year +
            String.format("%04d", savedStudent.getId());

    savedStudent.setEnrollmentNumber(enrollmentNumber);

    Student finalStudent = studentRepository.save(savedStudent);

    //  Create response BEFORE using it
    StudentResponse response =
            StudentMapper.toResponse(finalStudent);

    // Send registration email
    try {
        StudentRegistrationEmail email =
                new StudentRegistrationEmail(response);

        emailService.sendEmail(
                email.getEmail(),
                email.getSubject(),
                email.buildBody()
        );
    } catch (Exception e) {
        System.out.println("Email sending failed: " + e.getMessage());
    }

    return response;
}


    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password");
        }

        if (user.getRole() != UserRole.STUDENT) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Access denied");
        }

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student profile not found"));

       String token = jwtUtil.generateToken(user);

        return StudentMapper.toLoginResponse(student, token);
    }

    @Override
    public ProfileResponse getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"));

        return StudentMapper.toProfileResponse(student);
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(String email,
                                         UpdateProfileRequest request,
                                         MultipartFile file) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"));

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

       if (request.getMobileNumber() != null) {

    if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Mobile number already in use"
        );
    }

    user.setMobileNumber(request.getMobileNumber());
    userRepository.save(user);
}

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