package com.internshipmanagementsystem.coordinator.controller;

import com.internshipmanagementsystem.coordinator.dto.CoordinatorLoginRequest;
import com.internshipmanagementsystem.coordinator.dto.CoordinatorLoginResponse;
import com.internshipmanagementsystem.coordinator.service.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coordinator")
@CrossOrigin(origins = "*")
public class CoordinatorController {

    @Autowired
    private CoordinatorService coordinatorService;

    @PostMapping("/auth/login")
    public ResponseEntity<CoordinatorLoginResponse> login(@RequestBody CoordinatorLoginRequest request) {
        CoordinatorLoginResponse response = coordinatorService.login(request);
        return ResponseEntity.ok(response);
    }

    
    @GetMapping("/auth/hash")
    public ResponseEntity<String> getHash(@RequestParam String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return ResponseEntity.ok(encoder.encode(password));
    }

}
