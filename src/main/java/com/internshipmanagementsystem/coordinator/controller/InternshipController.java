// package com.internshipmanagementsystem.coordinator.controller;


// import lombok.RequiredArgsConstructor;

// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/coordinator/internships")
// @RequiredArgsConstructor
// public class InternshipController {

//     private final InternshipService internshipService;

//     // =========================
//     // CREATE INTERNSHIP
//     // =========================
//     @PostMapping
//     public ResponseEntity<InternshipResponse> createInternship(
//             @AuthenticationPrincipal String email,
//             @RequestBody InternshipRequest request) {

//         return ResponseEntity.ok(
//                 internshipService.createInternship(email, request)
//         );
//     }

//     // =========================
//     // GET MY INTERNSHIPS
//     // =========================
//     @GetMapping
//     public ResponseEntity<List<InternshipResponse>> getMyInternships(
//             @AuthenticationPrincipal String email) {

//         return ResponseEntity.ok(
//                 internshipService.getMyInternships(email)
//         );
//     }

//     // =========================
//     // GET SINGLE INTERNSHIP
//     // =========================
//     @GetMapping("/{id}")
//     public ResponseEntity<InternshipResponse> getInternship(
//             @PathVariable Long id) {

//         return ResponseEntity.ok(
//                 internshipService.getInternship(id)
//         );
//     }

//     // =========================
//     // UPDATE INTERNSHIP
//     // =========================
//     @PutMapping("/{id}")
//     public ResponseEntity<InternshipResponse> updateInternship(
//             @AuthenticationPrincipal String email,
//             @PathVariable Long id,
//             @RequestBody InternshipRequest request) {

//         return ResponseEntity.ok(
//                 internshipService.updateInternship(email, id, request)
//         );
//     }

//     // =========================
//     // DELETE INTERNSHIP
//     // =========================
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteInternship(
//             @AuthenticationPrincipal String email,
//             @PathVariable Long id) {

//         internshipService.deleteInternship(email, id);
//         return ResponseEntity.noContent().build();
//     }
// }