package com.internshipmanagementsystem.notification.ForgottenPassword;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.internshipmanagementsystem.notification.ForgottenPassword.DTO.ForgotPasswordRequest;
import com.internshipmanagementsystem.notification.ForgottenPassword.DTO.ResetPasswordRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/{role}/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @PathVariable UserRole role,
            @Valid @RequestBody ForgotPasswordRequest request) {

        forgotPasswordService.sendOtp(request.email(), role);

        return ResponseEntity.ok(new ApiResponse("OTP sent successfully"));
    }

    @PostMapping("/{role}/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(
            @PathVariable UserRole role,
            @Valid @RequestBody ResetPasswordRequest request) {

        forgotPasswordService.resetPassword(
                request.email(),
                role,
                request.otp(),
                request.newPassword()
        );

        return ResponseEntity.ok(new ApiResponse("Password reset successful"));
    }
}