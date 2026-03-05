package com.internshipmanagementsystem.admin.mapper;



import com.internshipmanagementsystem.admin.dtos.LoginResponse;
import com.internshipmanagementsystem.admin.model.User;

public class AuthMapper {

    public static LoginResponse toLoginResponse(User user, String token) {
        if (user == null) return null;

        return LoginResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}