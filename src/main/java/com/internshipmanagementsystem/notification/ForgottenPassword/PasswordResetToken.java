package com.internshipmanagementsystem.notification.ForgottenPassword;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"email", "role"})
       })
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

    private String role; // STUDENT / MENTOR / COORDINATOR

    private String otp;

    private LocalDateTime expiryTime;

}