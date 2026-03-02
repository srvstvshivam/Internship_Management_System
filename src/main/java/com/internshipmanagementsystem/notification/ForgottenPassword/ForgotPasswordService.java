package com.internshipmanagementsystem.notification.ForgottenPassword;

public interface ForgotPasswordService {

    void sendOtp(String email, UserRole role);

    void resetPassword(String email,
                       UserRole role,
                       String otp,
                       String newPassword);
}