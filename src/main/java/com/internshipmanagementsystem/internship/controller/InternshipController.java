package com.internshipmanagementsystem.internship.controller;

import com.internshipmanagementsystem.internship.dto.InternshipRequest;
import com.internshipmanagementsystem.internship.dto.InternshipResponse;
import com.internshipmanagementsystem.internship.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordinator/internships")
@CrossOrigin(origins = "*")
public class InternshipController {

    @Autowired
    private InternshipService internshipService;

    
    @PostMapping("/create")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<InternshipResponse> createInternship(
            @RequestBody InternshipRequest request,
            @RequestParam Long coordinatorId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(internshipService.createInternship(request, coordinatorId));
    }

   
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<InternshipResponse> updateInternship(
            @PathVariable Long id,
            @RequestBody InternshipRequest request) {
        return ResponseEntity.ok(internshipService.updateInternship(id, request));
    }

  
    @PutMapping("/{id}/publish")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<InternshipResponse> publishInternship(@PathVariable Long id) {
        return ResponseEntity.ok(internshipService.publishInternship(id));
    }

  
    @PutMapping("/{id}/close")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<InternshipResponse> closeInternship(@PathVariable Long id) {
        return ResponseEntity.ok(internshipService.closeInternship(id));
    }

    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<String> deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
        return ResponseEntity.ok("Internship deleted successfully");
    }

    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<InternshipResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(internshipService.getInternshipById(id));
    }

   
    @GetMapping("/all")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<List<InternshipResponse>> getAll() {
        return ResponseEntity.ok(internshipService.getAllInternships());
    }

   
    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<List<InternshipResponse>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(internshipService.getInternshipsByStatus(status));
    }

    
    @GetMapping("/my/{coordinatorId}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<List<InternshipResponse>> getByCoordinator(
            @PathVariable Long coordinatorId) {
        return ResponseEntity.ok(internshipService.getInternshipsByCoordinator(coordinatorId));
    }
}