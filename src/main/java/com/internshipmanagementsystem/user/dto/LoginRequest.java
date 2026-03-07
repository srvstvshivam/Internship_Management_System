package com.internshipmanagementsystem.user.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String identifier;   // loginId OR email OR mobile
    private String password;

}