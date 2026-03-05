package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.UserDTO;
import com.internshipmanagementsystem.admin.model.User;
import com.internshipmanagementsystem.admin.model.Enums.Role;

public interface UserService {

    UserDTO create(User user);

    UserDTO update(Long id, User updatedUser);

    void delete(Long id);

    void grantPostInternship(Long userId);

    void revokePostInternship(Long userId);

    long countByRole(Role role);
}