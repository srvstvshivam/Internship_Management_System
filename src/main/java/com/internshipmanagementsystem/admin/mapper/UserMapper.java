package com.internshipmanagementsystem.admin.mapper;

import com.internshipmanagementsystem.admin.dtos.CreateUserRequest;
import com.internshipmanagementsystem.admin.model.User;
// import com.internshipmanagementsystem.admin.model.Enum.Role;

public class UserMapper {

    public static User toEntity(CreateUserRequest request, String encodedPassword) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                // .role(request.getRole())
                .firstLogin(true)
                .build();
    }
}