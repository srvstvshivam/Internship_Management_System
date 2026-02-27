package com.internshipmanagementsystem.notification.ForgottenPassword;

public interface ForgotPasswordService {

    void sendOtp(String email, String role);

    void resetPassword(String email,
                       String role,
                       String otp,
                       String newPassword);
}