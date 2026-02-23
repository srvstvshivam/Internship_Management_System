package com.internshipmanagementsystem.student.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private Long id;
    private String email;
    private String firstName;
    private String MiddleName;
    private String lastName;
    private String message;
    private String role;
    private String token;   
}