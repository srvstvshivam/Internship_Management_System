package com.internshipmanagementsystem.user.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
        @NotBlank String email,
        @NotBlank String otp,
        @NotBlank String newPassword
) {}