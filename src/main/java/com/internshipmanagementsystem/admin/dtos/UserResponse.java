package com.internshipmanagementsystem.admin.dtos;



import com.internshipmanagementsystem.admin.model.Enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private boolean firstLogin;
}