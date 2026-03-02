package com.internshipmanagementsystem.notification.ForgottenPassword.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(
        @Email @NotBlank String email
) {}