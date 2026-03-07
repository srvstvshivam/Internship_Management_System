package com.internshipmanagementsystem.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.internshipmanagementsystem.user.dto.ApiResponse;
import com.internshipmanagementsystem.user.dto.ForgotPasswordRequest;
import com.internshipmanagementsystem.user.dto.ResetPasswordRequest;
import com.internshipmanagementsystem.user.service.ForgotPasswordService;

@RestController
@RequestMapping("/api/auth/student")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request) {

        forgotPasswordService.sendOtp(request.email() );

        return ResponseEntity.ok(
                new ApiResponse("If the email is registered, an OTP has been sent.")
        );
    }

   @PostMapping("/reset-password")
public ResponseEntity<ApiResponse> resetPassword(
        @Valid @RequestBody ResetPasswordRequest request) {

    forgotPasswordService.resetPassword(
            request.email(),
            request.otp(),
            request.newPassword()
    );

    return ResponseEntity.ok(
            new ApiResponse("Password reset successful")
    );
}
}