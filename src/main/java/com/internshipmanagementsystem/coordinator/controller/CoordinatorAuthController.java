

// package com.internshipmanagementsystem.coordinator.controller;

// import com.internshipmanagementsystem.coordinator.dto.CoordinatorLoginRequest;


// import jakarta.validation.Valid;
// import lombok.RequiredArgsConstructor;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/coordinator/auth")
// @RequiredArgsConstructor
// public class CoordinatorAuthController {

//     private final CoordinatorService coordinatorService;

//     @PostMapping("/login")
//     public ResponseEntity<CoordinatorLoginResponse> login(
//             @Valid @RequestBody CoordinatorLoginRequest request) {

//         CoordinatorLoginResponse response = coordinatorService.login(request);

//         return ResponseEntity.ok(response);
//     }
// }