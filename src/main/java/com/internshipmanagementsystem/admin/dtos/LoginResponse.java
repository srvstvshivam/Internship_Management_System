package com.internshipmanagementsystem.admin.dtos;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;
    private String email;
    private String role;
    private boolean firstLogin;
}