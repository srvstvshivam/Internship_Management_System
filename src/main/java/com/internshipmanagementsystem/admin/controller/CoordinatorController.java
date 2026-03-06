package com.internshipmanagementsystem.admin.controller;

import com.internshipmanagementsystem.admin.dtos.CoordinatorRegisterDTO;
import com.internshipmanagementsystem.admin.dtos.CoordinatorResponseDTO;
import com.internshipmanagementsystem.admin.service.CoordinatorService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/coordinators")
@RequiredArgsConstructor
public class CoordinatorController {

    private final CoordinatorService service;

    // CREATE COORDINATOR
    @PostMapping("/create")
    public CoordinatorResponseDTO createCoordinator(@RequestBody CoordinatorRegisterDTO dto) {
        return service.createCoordinator(dto);
    }

    // GET ALL COORDINATORS
    @GetMapping("/all")
    public List<CoordinatorResponseDTO> getAllCoordinators() {
        return service.getAllCoordinators();
    }

    // TOGGLE ACTIVE STATUS
    @PutMapping("/{id}/toggle-active")
    public CoordinatorResponseDTO toggleActive(@PathVariable Long id) {
        return service.toggleActive(id);
    }

    // ASSIGN ROLE
    @PutMapping("/{id}/assign-role")
    public CoordinatorResponseDTO assignRole(
            @PathVariable Long id,
            @RequestParam String role) {

        return service.assignRole(id, role);
    }

    // DELETE COORDINATOR
    @DeleteMapping("/{id}")
    public String deleteCoordinator(@PathVariable Long id) {
        service.deleteCoordinator(id);
        return "Coordinator deleted successfully";
    }
}