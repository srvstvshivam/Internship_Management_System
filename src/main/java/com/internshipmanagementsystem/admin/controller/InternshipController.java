package com.internshipmanagementsystem.admin.controller;

import com.internshipmanagementsystem.admin.dtos.InternshipDTO;
import com.internshipmanagementsystem.admin.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService service;

    // Create Internship - only ADMIN can create
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("created")
    public ResponseEntity<InternshipDTO> create(@RequestBody InternshipDTO internship) {
        InternshipDTO created = service.create(internship);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update Internship - only ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<InternshipDTO> update(@PathVariable Long id,
                                                @RequestBody InternshipDTO internship) {
        InternshipDTO updated = service.update(id, internship);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updated);
    }

    // Delete Internship - only ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }

    // Get All Internships - ADMIN or COORDINATOR
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINATOR')")
    @GetMapping("/all")
    public ResponseEntity<List<InternshipDTO>> getAll() {
        List<InternshipDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // Get Internship by ID - ADMIN or COORDINATOR
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<InternshipDTO> getById(@PathVariable Long id) {
        InternshipDTO internship = service.getById(id);
        if (internship == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(internship);
    }
}