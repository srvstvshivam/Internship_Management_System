package com.internshipmanagementsystem.admin.dtos;


public class AdminResponse {
    private String token;
    private String email;
    private String role;

    public AdminResponse(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public String getToken() { return token; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}