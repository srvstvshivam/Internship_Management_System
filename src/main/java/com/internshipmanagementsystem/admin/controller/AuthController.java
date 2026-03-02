package com.internshipmanagementsystem.admin.controller;

import com.internshipmanagementsystem.admin.service.AuthService;
import com.internshipmanagementsystem.admin.dtos.LoginRequest;
import com.internshipmanagementsystem.admin.dtos.ChangePasswordRequest;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestParam String email,
                               @Valid @RequestBody ChangePasswordRequest request) {
        authService.changePassword(email, request);
    }
}