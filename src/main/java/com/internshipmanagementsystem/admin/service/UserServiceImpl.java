package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.UserDTO;
import com.internshipmanagementsystem.admin.mapper.UserMapper;
import com.internshipmanagementsystem.admin.model.Enums.Role;
import com.internshipmanagementsystem.admin.model.Permission;
import com.internshipmanagementsystem.admin.model.User;
import com.internshipmanagementsystem.admin.repository.PermissionRepository;
import com.internshipmanagementsystem.admin.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO create(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword())); // 🔐 Important
        user.setActive(true);

        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO update(Long id, User updatedUser) {

        User existing = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        existing.setFullName(updatedUser.getFullName());
        existing.setEmail(updatedUser.getEmail());
        existing.setPhone(updatedUser.getPhone());
        existing.setDepartment(updatedUser.getDepartment());
        existing.setRole(updatedUser.getRole());
        existing.setActive(updatedUser.getActive());

        // Agar password change ho raha hai to encode karo
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        User savedUser = userRepository.save(existing);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {

        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void grantPostInternship(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Permission permission = permissionRepository.findByName("POST_INTERNSHIP")
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));

        if (!user.getPermissions().contains(permission)) {
            user.getPermissions().add(permission);
        }

        userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void revokePostInternship(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.getPermissions().removeIf(
                permission -> permission.getName().equals("POST_INTERNSHIP")
        );

        userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public long countByRole(Role role) {
        return userRepository.countByRole(role);
    }
}