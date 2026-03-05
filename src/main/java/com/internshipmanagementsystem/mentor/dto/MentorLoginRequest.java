package com.internshipmanagementsystem.mentor.dto;

import lombok.Data;

@Data
public class MentorLoginRequest {
    private String email;
    private String password;
}