package com.internshipmanagementsystem.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.internshipmanagementsystem.admin.dtos.CreateUserRequest;
import com.internshipmanagementsystem.admin.dtos.UpdateUserRequest;
import com.internshipmanagementsystem.admin.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // ---------------- CREATE USER ----------------
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        adminService.createUser(request);

        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("message", "User created successfully");

        return ResponseEntity.ok(response);
    }

    // ---------------- UPDATE USER ----------------
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody UpdateUserRequest request) {
        adminService.updateUser(id, request);

        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("message", "User updated successfully");

        return ResponseEntity.ok(response);
    }

    // ---------------- DELETE USER ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("message", "User deleted successfully");

        return ResponseEntity.ok(response);
    }
}