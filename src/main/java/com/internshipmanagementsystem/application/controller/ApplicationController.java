package com.internshipmanagementsystem.application.controller;

import com.internshipmanagementsystem.application.dto.ApplicationResponse;
import com.internshipmanagementsystem.application.dto.ApplicationStatusUpdateRequest;
import com.internshipmanagementsystem.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordinator/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

   
    @GetMapping("/all")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

   
    @GetMapping("/internship/{internshipId}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<List<ApplicationResponse>> getByInternship(
            @PathVariable Long internshipId) {
        return ResponseEntity.ok(
                applicationService.getApplicationsByInternship(internshipId));
    }

   
    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<List<ApplicationResponse>> getByStatus(
            @PathVariable String status) {
        return ResponseEntity.ok(
                applicationService.getApplicationsByStatus(status));
    }

   
    @GetMapping("/internship/{internshipId}/status/{status}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<List<ApplicationResponse>> getByInternshipAndStatus(
            @PathVariable Long internshipId,
            @PathVariable String status) {
        return ResponseEntity.ok(
                applicationService.getApplicationsByInternshipAndStatus(
                        internshipId, status));
    }

   
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<ApplicationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }

    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @PathVariable Long id,
            @RequestBody ApplicationStatusUpdateRequest request) {
        return ResponseEntity.ok(
                applicationService.updateApplicationStatus(id, request));
    }

    
    @PutMapping("/{id}/select")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<ApplicationResponse> select(
            @PathVariable Long id,
            @RequestParam Long coordinatorId,
            @RequestParam(required = false, defaultValue = "") String remarks) {
        return ResponseEntity.ok(
                applicationService.selectApplication(id, coordinatorId, remarks));
    }

    
    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('COORDINATOR')")
    public ResponseEntity<ApplicationResponse> reject(
            @PathVariable Long id,
            @RequestParam Long coordinatorId,
            @RequestParam(required = false, defaultValue = "") String remarks) {
        return ResponseEntity.ok(
                applicationService.rejectApplication(id, coordinatorId, remarks));
    }
}