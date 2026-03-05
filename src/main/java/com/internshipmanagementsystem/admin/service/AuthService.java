package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.LoginRequest;
import com.internshipmanagementsystem.admin.dtos.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}