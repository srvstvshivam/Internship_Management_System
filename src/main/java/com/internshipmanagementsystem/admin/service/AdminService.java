package com.internshipmanagementsystem.admin.service;


import com.internshipmanagementsystem.admin.dtos.AdminResponse;
import com.internshipmanagementsystem.admin.dtos.LoginRequest;

public interface AdminService {
    AdminResponse login(LoginRequest request);
}
