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
    public void sendOtp(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        // Prevent email enumeration
        if (optionalUser.isEmpty()) {
            return;
        }

        User user = optionalUser.get();
        UserRole role = user.getRole();

        Optional<PasswordResetToken> existing = tokenRepository.findByUserAndRole(user, role);

        PasswordResetToken token;
        String rawOtp;

        if (existing.isPresent()) {

            token = existing.get();

            // Block resend if OTP still valid
            if (token.getExpiryTime().isAfter(LocalDateTime.now())) {

                long secondsRemaining = java.time.Duration.between(
                        LocalDateTime.now(),
                        token.getExpiryTime()).getSeconds();

                throw new ResponseStatusException(
                        HttpStatus.TOO_MANY_REQUESTS,
                        "OTP already sent. Try again in " + secondsRemaining + " seconds.");
            }

            // OTP expired → generate new OTP
            rawOtp = generateOtp();

            token.setOtp(passwordEncoder.encode(rawOtp));
            token.setExpiryTime(LocalDateTime.now().plusMinutes(2));
            token.setAttemptCount(0);
            token.setLockedUntil(null);

        } else {

            // First time OTP request
            rawOtp = generateOtp();

            token = PasswordResetToken.builder()
                    .user(user)
                    .role(role)
                    .otp(passwordEncoder.encode(rawOtp))
                    .expiryTime(LocalDateTime.now().plusMinutes(2))
                    .attemptCount(0)
                    .lockedUntil(null)
                    .build();
        }

        tokenRepository.save(token);
        String emailBody = """
                <html>
                <head>
                <style>
                body {font-family: Arial; background:#f5f6fa; padding:20px;}
                .container {max-width:600px;background:white;margin:auto;padding:30px;border-radius:8px;text-align:center;}
                .title {font-size:22px;font-weight:bold;color:#2c3e50;}
                .otp {font-size:26px;font-weight:bold;color:#0a58ca;margin:20px 0;}
                .footer {font-size:12px;color:#777;margin-top:20px;}
                </style>
                </head>

                <body>

                <div class="container">

                <p class="title">Password Reset Request</p>

                <p>
                We received a request to reset your password for the
                <b>CDAC Internship Portal</b>.
                </p>

                <p>Your OTP for password reset is:</p>

                <div class="otp">%s</div>

                <p>This OTP is valid for <b>2 minutes</b>.</p>

                <p>If you did not request this, please ignore this email.</p>

                <div class="footer">
                CDAC Internship Portal <br>
                This is an automated email. Please do not reply.
                </div>

                </div>

                </body>
                </html>
                """
                .formatted(rawOtp);

        emailService.sendEmail(
                user.getEmail(),
                "CDAC Internship Portal - Password Reset OTP",
                emailBody);
    }

    @Override
    public void resetPassword(String email, String otp, String newPassword) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid request");
        }

        User user = optionalUser.get();
        UserRole role = user.getRole(); // role from database

        PasswordResetToken token = tokenRepository.findByUserAndRole(user, role)
                .orElseThrow(() -> new ResponseStatusException(
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