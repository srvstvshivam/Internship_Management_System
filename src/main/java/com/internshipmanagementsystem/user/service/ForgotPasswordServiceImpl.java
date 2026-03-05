package com.internshipmanagementsystem.user.service;

import com.internshipmanagementsystem.notification.EmailService;
import com.internshipmanagementsystem.user.model.PasswordResetToken;
import com.internshipmanagementsystem.user.model.User;
import com.internshipmanagementsystem.user.model.Enums.UserRole;
import com.internshipmanagementsystem.user.repository.PasswordResetTokenRepository;
import com.internshipmanagementsystem.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private final SecureRandom secureRandom = new SecureRandom();

    private String generateOtp() {
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }

   @Override
public void sendOtp(String email, UserRole role) {

    Optional<User> optionalUser = userRepository.findByEmail(email);

    if (optionalUser.isEmpty()) {
        return;
    }

    User user = optionalUser.get();

    if (user.getRole() != role) {
        return;
    }

    Optional<PasswordResetToken> existing =
            tokenRepository.findByUserAndRole(user, role);

    if (existing.isPresent()) {

        PasswordResetToken token = existing.get();

        if (token.getLockedUntil() != null &&
                token.getLockedUntil().isAfter(LocalDateTime.now())) {

            return;
        }

        if (token.getExpiryTime().isAfter(LocalDateTime.now())) {
            return;
        }

        tokenRepository.delete(token);
    }

    String rawOtp = generateOtp();
    String encodedOtp = passwordEncoder.encode(rawOtp);

    PasswordResetToken newToken = PasswordResetToken.builder()
            .user(user)
            .role(role)
            .otp(encodedOtp)
            .expiryTime(LocalDateTime.now().plusMinutes(10))
            .attemptCount(0)
            .lockedUntil(null)
            .build();

    tokenRepository.save(newToken);

    emailService.sendEmail(
            email,
            "Password Reset OTP",
            "Your OTP is: " + rawOtp + "\nValid for 10 minutes."
    );
}
@Override
public void resetPassword(String email,
                          UserRole role,
                          String otp,
                          String newPassword) {

    Optional<User> optionalUser = userRepository.findByEmail(email);

    if (optionalUser.isEmpty()) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Invalid request");
    }

    User user = optionalUser.get();

    if (user.getRole() != role) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Invalid request");
    }

    PasswordResetToken token =
            tokenRepository.findByUserAndRole(user, role)
                    .orElseThrow(() ->
                            new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST,
                                    "Invalid or expired OTP"));

    if (token.getLockedUntil() != null &&
            token.getLockedUntil().isAfter(LocalDateTime.now())) {

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Account locked. Try again later.");
    }

    if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Invalid or expired OTP");
    }

    if (!passwordEncoder.matches(otp, token.getOtp())) {

        token.setAttemptCount(token.getAttemptCount() + 1);

        if (token.getAttemptCount() >= 5) {
            token.setLockedUntil(LocalDateTime.now().plusHours(24));
        }

        tokenRepository.save(token);

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Invalid or expired OTP");
    }

    if (newPassword.length() < 8) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Password must be at least 8 characters");
    }

    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);

    tokenRepository.delete(token);
}
}