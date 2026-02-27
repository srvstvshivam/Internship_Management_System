package com.internshipmanagementsystem.notification.ForgottenPassword;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/{role}/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @PathVariable("role") String role,
            @RequestParam("email") String email) {

        forgotPasswordService.sendOtp(email, role);
        return ResponseEntity.ok("OTP sent");
    }

    @PostMapping("/{role}/reset-password")
    public ResponseEntity<String> resetPassword(
            @PathVariable("role") String role,
            @RequestParam("email") String email,
            @RequestParam("otp") String otp,
            @RequestParam("newPassword") String newPassword) {

        forgotPasswordService.resetPassword(email, role, otp, newPassword);
        return ResponseEntity.ok("Password reset successful");
    }
}