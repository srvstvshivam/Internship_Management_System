package com.internshipmanagementsystem.admin.controller;

import com.internshipmanagementsystem.admin.dtos.CoordinatorRegisterDTO;
import com.internshipmanagementsystem.admin.dtos.CoordinatorResponseDTO;
import com.internshipmanagementsystem.admin.dtos.CoordinatorUpdateDTO;
import com.internshipmanagementsystem.admin.service.CoordinatorService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coordinator")
@RequiredArgsConstructor
public class CoordinatorController {

    private final CoordinatorService service;

    @PostMapping("/register")
    public ResponseEntity<CoordinatorResponseDTO> register(@RequestBody CoordinatorRegisterDTO dto) {
        return ResponseEntity.ok(service.registerCoordinator(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CoordinatorResponseDTO> update(@PathVariable Long id, @RequestBody CoordinatorUpdateDTO dto) {
        return ResponseEntity.ok(service.updateCoordinator(id, dto));
    }
}