package com.internshipmanagementsystem.admin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.internshipmanagementsystem.admin.service.UserService;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/permission/{id}/grant")
    public void grant(@PathVariable Long id) {
        userService.grantPostInternship(id);
    }

    @PostMapping("/permission/{id}/revoke")
    public void revoke(@PathVariable Long id) {
        userService.revokePostInternship(id);
    }
}