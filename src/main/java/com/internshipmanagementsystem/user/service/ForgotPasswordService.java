package com.internshipmanagementsystem.user.service;

import com.internshipmanagementsystem.user.model.Enums.UserRole;

public interface ForgotPasswordService {

    void sendOtp(String email, UserRole role);

    void resetPassword(String email,
                       UserRole role,
                       String otp,
                       String newPassword);
}