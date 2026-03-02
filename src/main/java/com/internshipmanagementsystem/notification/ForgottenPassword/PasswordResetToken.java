package com.internshipmanagementsystem.notification.ForgottenPassword;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens",
       uniqueConstraints = @UniqueConstraint(columnNames = {"email", "role"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String otp; // hashed OTP

    private LocalDateTime expiryTime;

    private int attemptCount;
}