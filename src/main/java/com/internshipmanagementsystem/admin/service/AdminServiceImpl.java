package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.AdminResponse;
import com.internshipmanagementsystem.admin.dtos.LoginRequest;
import com.internshipmanagementsystem.config.JwtUtil;
import com.internshipmanagementsystem.user.model.User;
import com.internshipmanagementsystem.user.model.Enums.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AdminResponse login(LoginRequest request) {
        
        String adminEmail = "admin@gmail.com";
        String adminPassword = "admin123";

        // Validate credentials
        if (!request.getEmail().equals(adminEmail) ||
            !request.getPassword().equals(adminPassword)) {
            throw new RuntimeException("Invalid email or password");
        }

        // Create a temporary User object to satisfy the JwtUtil requirement
        User adminUser = new User();
        adminUser.setEmail(adminEmail);
        
        // Set role as enum
        adminUser.setRole(UserRole.ADMIN);

        // Generate token using the User object
        String token = jwtUtil.generateToken(adminUser);

        return new AdminResponse(token, adminEmail, "ADMIN");
    }
}