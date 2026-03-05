package com.internshipmanagementsystem.admin.mapper;


import com.internshipmanagementsystem.admin.dtos.UserDTO;
import com.internshipmanagementsystem.admin.model.User;
import com.internshipmanagementsystem.admin.model.Enums.Role;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .department(user.getDepartment())
                .role(user.getRole())
                .mentorId(user.getMentor() != null ? user.getMentor().getId() : null)
                .build();
    }
}