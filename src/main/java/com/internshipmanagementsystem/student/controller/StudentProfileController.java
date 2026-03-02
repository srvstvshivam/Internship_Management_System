package com.internshipmanagementsystem.student.controller;

import com.internshipmanagementsystem.student.dto.*;
import com.internshipmanagementsystem.student.service.*;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/students/profile")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentService studentService;
    private final EducationService educationService;
    private final ProjectService projectService;
    private final WorkExperienceService workExperienceService;

    // =========================
    // PROFILE
    // =========================

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile(
            @AuthenticationPrincipal String email) {

        return ResponseEntity.ok(
                studentService.getProfile(email)
        );
    }

    @PutMapping
    public ResponseEntity<ProfileResponse> updateProfile(
            @AuthenticationPrincipal String email,
            @RequestBody UpdateProfileRequest request,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        return ResponseEntity.ok(
                studentService.updateProfile(email, request, file)
        );
    }

    // =========================
    // EDUCATION
    // =========================

    @GetMapping("/educations")
    public ResponseEntity<EducationListResponse> getEducations(
            @AuthenticationPrincipal String email) {

        return ResponseEntity.ok(
                educationService.getEducations(email)
        );
    }

    @PostMapping("/educations")
    public ResponseEntity<EducationResponse> addEducation(
            @AuthenticationPrincipal String email,
            @RequestBody EducationRequest request) {

        return ResponseEntity.ok(
                educationService.addEducation(email, request)
        );
    }

    @PutMapping("/educations/{id}")
    public ResponseEntity<EducationResponse> updateEducation(
            @AuthenticationPrincipal String email,
            @PathVariable Long id,
            @RequestBody EducationRequest request) {

        return ResponseEntity.ok(
                educationService.updateEducation(email, id, request)
        );
    }

    @DeleteMapping("/educations/{id}")
    public ResponseEntity<Void> deleteEducation(
            @AuthenticationPrincipal String email,
            @PathVariable Long id) {

        educationService.deleteEducation(email, id);
        return ResponseEntity.noContent().build();
    }

    // =========================
    // PROJECTS
    // =========================

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponse>> getProjects(
            @AuthenticationPrincipal String email) {

        return ResponseEntity.ok(
                projectService.getProjects(email)
        );
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectResponse> addProject(
            @AuthenticationPrincipal String email,
            @RequestBody ProjectRequest request) {

        return ResponseEntity.ok(
                projectService.addProject(email, request)
        );
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @AuthenticationPrincipal String email,
            @PathVariable Long id,
            @RequestBody ProjectRequest request) {

        return ResponseEntity.ok(
                projectService.updateProject(email, id, request)
        );
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(
            @AuthenticationPrincipal String email,
            @PathVariable Long id) {

        projectService.deleteProject(email, id);
        return ResponseEntity.noContent().build();
    }

    // =========================
    // WORK EXPERIENCE
    // =========================

    @GetMapping("/work-experiences")
    public ResponseEntity<List<WorkExperienceResponse>> getWorkExperiences(
            @AuthenticationPrincipal String email) {

        return ResponseEntity.ok(
                workExperienceService.getExperiences(email)
        );
    }

    @PostMapping("/work-experiences")
    public ResponseEntity<WorkExperienceResponse> addWorkExperience(
            @AuthenticationPrincipal String email,
            @RequestBody WorkExperienceRequest request) {

        return ResponseEntity.ok(
                workExperienceService.addExperience(email, request)
        );
    }

    @PutMapping("/work-experiences/{id}")
    public ResponseEntity<WorkExperienceResponse> updateWorkExperience(
            @AuthenticationPrincipal String email,
            @PathVariable Long id,
            @RequestBody WorkExperienceRequest request) {

        return ResponseEntity.ok(
                workExperienceService.updateExperience(email, id, request)
        );
    }

    @DeleteMapping("/work-experiences/{id}")
    public ResponseEntity<Void> deleteWorkExperience(
            @AuthenticationPrincipal String email,
            @PathVariable Long id) {

        workExperienceService.deleteExperience(email, id);
        return ResponseEntity.noContent().build();
    }
}