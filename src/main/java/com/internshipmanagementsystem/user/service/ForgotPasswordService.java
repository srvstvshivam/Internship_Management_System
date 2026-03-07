package com.internshipmanagementsystem.user.service;

import com.internshipmanagementsystem.user.model.Enums.UserRole;

public interface ForgotPasswordService {

    void sendOtp(String email);

 void resetPassword(String email, String otp, String newPassword);
}