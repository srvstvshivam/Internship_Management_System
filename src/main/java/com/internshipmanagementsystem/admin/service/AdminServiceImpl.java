package com.internshipmanagementsystem.admin.service;


import com.internshipmanagementsystem.admin.dtos.AdminResponse;
import com.internshipmanagementsystem.admin.dtos.LoginRequest;
import com.internshipmanagementsystem.admin.configuration.AdminJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminJwtUtil jwtUtil;

    @Override
    public AdminResponse login(LoginRequest request) {

        
        String Email = "admin@gmail.com";
        String Password = "admin123";
        String Role = "ADMIN";

        // Validate credentials
        if (!request.getEmail().equals(Email) ||
            !request.getPassword().equals(Password)) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate token using AdminJwtUtil
        String token = jwtUtil.generateToken(Email, Role);

        return new AdminResponse(token, Email, Role);
    }
}
