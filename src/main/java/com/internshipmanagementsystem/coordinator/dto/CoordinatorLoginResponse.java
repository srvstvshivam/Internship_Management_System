package com.internshipmanagementsystem.coordinator.dto;

public class CoordinatorLoginResponse {
    private String token;
    private String email;
    private String role;

    public CoordinatorLoginResponse(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public String getToken() { return token; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}