package com.internshipmanagementsystem.admin.dtos;

import com.internshipmanagementsystem.admin.model.Enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private String department;

    private Role role;

    private Long mentorId;
}