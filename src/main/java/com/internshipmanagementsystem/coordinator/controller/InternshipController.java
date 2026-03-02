package com.internshipmanagementsystem.coordinator.controller;

import com.internshipmanagementsystem.coordinator.dto.InternshipRequest;
import com.internshipmanagementsystem.coordinator.dto.InternshipResponse;
import com.internshipmanagementsystem.coordinator.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coordinator/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping
    public ResponseEntity<InternshipResponse> create(@AuthenticationPrincipal String email, @RequestBody InternshipRequest request) {
        return ResponseEntity.ok(internshipService.createInternship(email, request));
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<InternshipResponse> publish(@AuthenticationPrincipal String email, @PathVariable Long id) {
        // You'll need to add this method to the Interface too!
        return ResponseEntity.ok(internshipService.publishInternship(email, id));
    }

    @GetMapping
    public ResponseEntity<List<InternshipResponse>> getMyInternships(@AuthenticationPrincipal String email) {
        return ResponseEntity.ok(internshipService.getMyInternships(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(internshipService.getInternship(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternshipResponse> update(@AuthenticationPrincipal String email, @PathVariable Long id, @RequestBody InternshipRequest request) {
        return ResponseEntity.ok(internshipService.updateInternship(email, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal String email, @PathVariable Long id) {
        internshipService.deleteInternship(email, id);
        return ResponseEntity.noContent().build();
    }
}