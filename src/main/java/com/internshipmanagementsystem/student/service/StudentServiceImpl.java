package com.internshipmanagementsystem.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.internshipmanagementsystem.config.CloudinaryService;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.internshipmanagementsystem.student.model.Address;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;
    private final EmailService emailService;

    private String generateEnrollmentNumber() {

        String year = String.valueOf(LocalDate.now().getYear());

        String randomPart = UUID.randomUUID()
                .toString()
                .substring(0, 5)
                .toUpperCase();

        return "STU" + year + randomPart;
    }

    @Transactional
    @Override
    public StudentResponse registerStudent(StudentRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email already registered");
        }

        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Mobile number already registered");
        }

        String enrollmentNumber = generateEnrollmentNumber();

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .loginId(enrollmentNumber)
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .password(encodedPassword)
                .role(UserRole.STUDENT)
                .build();

        User savedUser = userRepository.save(user);

        Student student = StudentMapper.toEntity(request);
        student.setUser(savedUser);
        student.setEnrollmentNumber(enrollmentNumber);

        Student savedStudent = studentRepository.save(student);

        StudentResponse response = StudentMapper.toResponse(savedStudent);

        try {

            StudentRegistrationEmail email =
                    new StudentRegistrationEmail(response);

            emailService.sendEmail(
                    email.getEmail(),
                    email.getSubject(),
                    email.buildBody());

        } catch (Exception e) {
            System.out.println("Email sending failed: " + e.getMessage());
        }

        return response;
    }

    @Override
    public ProfileResponse getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"));

        return StudentMapper.toProfileResponse(student);
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(String email, UpdateProfileRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
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

            if (!user.getMobileNumber().equals(request.getMobileNumber()) &&
                    userRepository.existsByMobileNumber(request.getMobileNumber())) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Mobile number already in use");
            }

            user.setMobileNumber(request.getMobileNumber());
            userRepository.save(user);
        }

        if (request.getPermanentAddress() != null) {

            AddressRequest addr = request.getPermanentAddress();

            Address permanent = Address.builder()
                    .street(addr.getStreet())
                    .city(addr.getCity())
                    .state(addr.getState())
                    .country(addr.getCountry())
                    .pincode(addr.getPincode())
                    .build();

            student.setPermanentAddress(permanent);
        }

        if (request.getCorrespondenceAddress() != null) {

            AddressRequest addr = request.getCorrespondenceAddress();

            Address correspondence = Address.builder()
                    .street(addr.getStreet())
                    .city(addr.getCity())
                    .state(addr.getState())
                    .country(addr.getCountry())
                    .pincode(addr.getPincode())
                    .build();

            student.setCorrespondenceAddress(correspondence);

        } else if (request.getPermanentAddress() != null) {

            student.setCorrespondenceAddress(student.getPermanentAddress());
        }

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.toProfileResponse(updatedStudent);
    }

    @Override
    @Transactional
    public ProfileImageResponse uploadProfileImage(String email, MultipartFile file) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"));

        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "File cannot be empty");
        }

        String imageUrl = cloudinaryService.uploadFile(file, "student/profile");

        student.setProfileImageUrl(imageUrl);

        studentRepository.save(student);

        return new ProfileImageResponse(imageUrl);
    }
}