package com.internshipmanagementsystem.notification.ForgottenPassword;

import com.internshipmanagementsystem.notification.EmailService;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final PasswordResetTokenRepository tokenRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private final SecureRandom secureRandom = new SecureRandom();

    private String generateOtp() {
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public void sendOtp(String email, UserRole role) {

        boolean exists = switch (role) {
            case STUDENT -> studentRepository.findByEmail(email).isPresent();
            case MENTOR -> false;      // Add repository logic
            case COORDINATOR -> false; // Add repository logic
        };

        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found");
        }

        String rawOtp = generateOtp();
        String encodedOtp = passwordEncoder.encode(rawOtp);

        tokenRepository.deleteByEmailAndRole(email, role);

        PasswordResetToken token = PasswordResetToken.builder()
                .email(email)
                .role(role)
                .otp(encodedOtp)
                .expiryTime(LocalDateTime.now().plusMinutes(10))
                .attemptCount(0)
                .build();

        tokenRepository.save(token);

        String subject = "Password Reset OTP";
        String body = """
                Your OTP for password reset is: %s

                Valid for 10 minutes.
                """.formatted(rawOtp);

        emailService.sendEmail(email, subject, body);
    }

    @Override
    @Transactional
    public void resetPassword(String email,
                              UserRole role,
                              String otp,
                              String newPassword) {

        PasswordResetToken token = tokenRepository
                .findByEmailAndRole(email, role)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request"));

        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP expired");
        }

        if (!passwordEncoder.matches(otp, token.getOtp())) {
            token.setAttemptCount(token.getAttemptCount() + 1);
            if (token.getAttemptCount() >= 5) {
                tokenRepository.delete(token);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Too many attempts");
            }
            tokenRepository.save(token);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP");
        }

        switch (role) {
            case STUDENT -> {
                Student student = studentRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

                student.setPassword(passwordEncoder.encode(newPassword));
                studentRepository.save(student);
            }
            case MENTOR -> {
                // Add mentor logic
            }
            case COORDINATOR -> {
                // Add coordinator logic
            }
        }

        tokenRepository.delete(token);
    }
}