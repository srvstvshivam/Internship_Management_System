package com.internshipmanagementsystem.admin.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}