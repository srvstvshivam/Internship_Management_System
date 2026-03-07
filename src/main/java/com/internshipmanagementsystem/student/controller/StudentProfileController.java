package com.internshipmanagementsystem.student.controller;

import com.internshipmanagementsystem.student.dto.*;
import com.internshipmanagementsystem.student.service.*;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/students/profile")
@RequiredArgsConstructor
public class StudentProfileController {

        private final StudentService studentService;
        private final EducationService educationService;
        private final ProjectService projectService;
        private final WorkExperienceService workExperienceService;
        private final SkillLanguageService skillLanguageService;
        private final CertificationService certificationService;
        private final StudentLinksResumeService linksResumeService;

        @GetMapping
        public ResponseEntity<ProfileResponse> getProfile() {

                String email = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();
                
                return ResponseEntity.ok(
                                studentService.getProfile(email));
        }

        @PutMapping
        public ResponseEntity<ProfileResponse> updateProfile(
                        @RequestBody UpdateProfileRequest request) {

                String email = SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName();

                return ResponseEntity.ok(
                                studentService.updateProfile(email, request));
        }

        @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<ProfileImageResponse> uploadProfileImage(
                        @AuthenticationPrincipal String email,
                        @RequestParam("file") MultipartFile file) {

                return ResponseEntity.ok(
                                studentService.uploadProfileImage(email, file));
        }

        @GetMapping("/educations")
        public ResponseEntity<EducationListResponse> getEducations(
                        @AuthenticationPrincipal String email) {

                return ResponseEntity.ok(
                                educationService.getEducations(email));
        }

        @PostMapping("/educations")
        public ResponseEntity<EducationResponse> addEducation(
                        @AuthenticationPrincipal String email,
                        @RequestBody EducationRequest request) {

                return ResponseEntity.ok(
                                educationService.addEducation(email, request));
        }

        @PutMapping("/educations/{id}")
        public ResponseEntity<EducationResponse> updateEducation(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id,
                        @RequestBody EducationRequest request) {

                return ResponseEntity.ok(
                                educationService.updateEducation(email, id, request));
        }

        @DeleteMapping("/educations/{id}")
        public ResponseEntity<Void> deleteEducation(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id) {

                educationService.deleteEducation(email, id);
                return ResponseEntity.noContent().build();
        }

        @PostMapping(value = "/educations/{id}/marksheet", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<String> uploadMarksheet(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id,
                        @RequestParam("file") MultipartFile file) {

                educationService.uploadMarksheet(email, id, file);

                return ResponseEntity.ok("Marksheet uploaded successfully");
        }

        @GetMapping("/educations/{id}/documents")
        public ResponseEntity<List<EducationDocumentResponse>> getEducationDocuments(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id) {

                return ResponseEntity.ok(
                                educationService.getEducationDocuments(email, id));
        }

        @DeleteMapping("/educations/{educationId}/documents/{documentId}")
        public ResponseEntity<Void> deleteDocument(
                        @AuthenticationPrincipal String email,
                        @PathVariable("educationId") Long educationId,
                        @PathVariable("documentId") Long documentId) {

                educationService.deleteDocument(email, educationId, documentId);

                return ResponseEntity.noContent().build();
        }

        @GetMapping("/projects")
        public ResponseEntity<List<ProjectResponse>> getProjects(
                        @AuthenticationPrincipal String email) {

                return ResponseEntity.ok(
                                projectService.getProjects(email));
        }

        @PostMapping("/projects")
        public ResponseEntity<ProjectResponse> addProject(
                        @AuthenticationPrincipal String email,
                        @RequestBody ProjectRequest request) {

                return ResponseEntity.ok(
                                projectService.addProject(email, request));
        }

        @PutMapping("/projects/{id}")
        public ResponseEntity<ProjectResponse> updateProject(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id,
                        @RequestBody ProjectRequest request) {

                return ResponseEntity.ok(
                                projectService.updateProject(email, id, request));
        }

        @DeleteMapping("/projects/{id}")
        public ResponseEntity<Void> deleteProject(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id) {

                projectService.deleteProject(email, id);
                return ResponseEntity.noContent().build();
        }

        @GetMapping("/work-experiences")
        public ResponseEntity<List<WorkExperienceResponse>> getWorkExperiences(
                        @AuthenticationPrincipal String email) {

                return ResponseEntity.ok(
                                workExperienceService.getExperiences(email));
        }

        @PostMapping("/work-experiences")
        public ResponseEntity<WorkExperienceResponse> addWorkExperience(
                        @AuthenticationPrincipal String email,
                        @RequestBody WorkExperienceRequest request) {

                return ResponseEntity.ok(
                                workExperienceService.addExperience(email, request));
        }

        @PutMapping("/work-experiences/{id}")
        public ResponseEntity<WorkExperienceResponse> updateWorkExperience(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id,
                        @RequestBody WorkExperienceRequest request) {

                return ResponseEntity.ok(
                                workExperienceService.updateExperience(email, id, request));
        }

        @DeleteMapping("/work-experiences/{id}")
        public ResponseEntity<Void> deleteWorkExperience(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id) {

                workExperienceService.deleteExperience(email, id);
                return ResponseEntity.noContent().build();
        }

        @GetMapping("/skills-languages")
        public ResponseEntity<List<SkillLanguageResponse>> getSkillsLanguages(
                        @AuthenticationPrincipal String email) {

                return ResponseEntity.ok(
                                skillLanguageService.getAll(email));
        }

        @PostMapping("/skills-languages")
        public ResponseEntity<SkillLanguageResponse> addSkillLanguage(
                        @AuthenticationPrincipal String email,
                        @RequestBody SkillLanguageRequest request) {

                return ResponseEntity.ok(
                                skillLanguageService.add(email, request));
        }

        @PutMapping("/skills-languages/{id}")
        public ResponseEntity<SkillLanguageResponse> updateSkillLanguage(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id,
                        @RequestBody SkillLanguageRequest request) {

                return ResponseEntity.ok(
                                skillLanguageService.update(email, id, request));
        }

        @DeleteMapping("/skills-languages/{id}")
        public ResponseEntity<Void> deleteSkillLanguage(
                        @AuthenticationPrincipal String email,
                        @PathVariable("id") Long id) {

                skillLanguageService.delete(email, id);

                return ResponseEntity.noContent().build();
        }
        @GetMapping("/certifications")
public ResponseEntity<List<CertificationResponse>> getCertifications(
        @AuthenticationPrincipal String email) {

    return ResponseEntity.ok(
            certificationService.getCertifications(email)
    );
}
@PostMapping("/certifications")
public ResponseEntity<CertificationResponse> addCertification(
        @AuthenticationPrincipal String email,
        @RequestBody CertificationRequest request) {

    return ResponseEntity.ok(
            certificationService.addCertification(email, request)
    );
}
@PutMapping("/certifications/{id}")
public ResponseEntity<CertificationResponse> updateCertification(
        @AuthenticationPrincipal String email,
         @PathVariable("id") Long id,
        @RequestBody CertificationRequest request) {

    return ResponseEntity.ok(
            certificationService.updateCertification(email, id, request)
    );
}
@DeleteMapping("/certifications/{id}")
public ResponseEntity<Void> deleteCertification(
        @AuthenticationPrincipal String email,
         @PathVariable("id") Long id) {

    certificationService.deleteCertification(email, id);

    return ResponseEntity.noContent().build();
}


@GetMapping("/links-resume")
public ResponseEntity<LinksResumeResponse> getLinksResume(
         @AuthenticationPrincipal String email) {
               

    return ResponseEntity.ok(
            linksResumeService.getLinksResume(email)
    );
}

@PutMapping("/links-resume")
public ResponseEntity<LinksResumeResponse> updateLinksResume(
       @AuthenticationPrincipal String email ,
        @RequestBody LinksResumeRequest request) {
       

    return ResponseEntity.ok(
            linksResumeService.updateLinksResume(email, request)
    );
}




@PostMapping(value="/resume", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<ResumeUploadResponse> uploadResume(
        @AuthenticationPrincipal String email,
        @RequestParam("file") MultipartFile file) {

    return ResponseEntity.ok(
            linksResumeService.uploadResume(email, file)
    );
}










}
