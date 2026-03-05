package com.internshipmanagementsystem.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.internshipmanagementsystem.user.model.Enums.UserRole;

@Entity
@Table(name = "password_reset_tokens",
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
@JoinColumn(name = "user_id", nullable = false)
private User user;  // ✅ Centralized reference

    @Enumerated(EnumType.STRING)
    private UserRole role;

     // optional (for sending mail)

    private String otp;

    private LocalDateTime expiryTime;

    private int attemptCount;

    private LocalDateTime lockedUntil;
}