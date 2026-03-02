package com.internshipmanagementsystem.notification.ForgottenPassword.DTO;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
        @NotBlank String email,
        @NotBlank String otp,
        @NotBlank String newPassword
) {}