package com.internshipmanagementsystem.admin.controller;

import com.internshipmanagementsystem.admin.dtos.LoginRequest;
import com.internshipmanagementsystem.admin.dtos.LoginResponse;
import com.internshipmanagementsystem.admin.dtos.UserDTO;
import com.internshipmanagementsystem.admin.model.User;
import com.internshipmanagementsystem.admin.service.AuthService;
import com.internshipmanagementsystem.admin.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;
    private final UserService userService;

    // 🔓 Login (Public)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // 🔐 Dashboard (ADMIN only)
    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "Welcome Admin!";
    }

    // ✅ Create User (DTO Based)
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> create(@RequestBody User user) {

        UserDTO createdUser = userService.create(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    // ✅ Update User
    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> update(
            @PathVariable Long id,
            @RequestBody User user) {

        UserDTO updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // ✅ Delete User
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}