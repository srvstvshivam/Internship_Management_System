package com.internshipmanagementsystem.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.internshipmanagementsystem.user.dto.ApiResponse;
import com.internshipmanagementsystem.user.dto.ForgotPasswordRequest;
import com.internshipmanagementsystem.user.dto.ResetPasswordRequest;
import com.internshipmanagementsystem.user.model.Enums.UserRole;
import com.internshipmanagementsystem.user.service.ForgotPasswordService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    private UserRole parseRole(String role) {
        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid role. Allowed values: STUDENT, MENTOR, COORDINATOR"
            );
        }
    }

    @PostMapping("/{role}/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @PathVariable("role") String role,
            @Valid @RequestBody ForgotPasswordRequest request) {

        UserRole userRole = parseRole(role);

        forgotPasswordService.sendOtp(request.email(), userRole);

        return ResponseEntity.ok(new ApiResponse("OTP sent successfully"));
    }

    @PostMapping("/{role}/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(
            @PathVariable("role") String role,
            @Valid @RequestBody ResetPasswordRequest request) {

        UserRole userRole = parseRole(role);

        forgotPasswordService.resetPassword(
                request.email(),
                userRole,
                request.otp(),
                request.newPassword()
        );

        return ResponseEntity.ok(new ApiResponse("Password reset successful"));
    }
}