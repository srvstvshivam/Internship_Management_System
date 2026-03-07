package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.LinksResumeRequest;
import com.internshipmanagementsystem.student.dto.LinksResumeResponse;
import com.internshipmanagementsystem.student.dto.ResumeUploadResponse;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.model.StudentLinksResume;
import com.internshipmanagementsystem.student.repository.StudentLinksResumeRepository;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import com.internshipmanagementsystem.student.mapper.StudentLinksResumeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;

import com.internshipmanagementsystem.config.CloudinaryService;

@Service
@RequiredArgsConstructor
public class StudentLinksResumeServiceImpl implements StudentLinksResumeService {

    private final StudentRepository studentRepository;
    private final StudentLinksResumeRepository repository;
    private final CloudinaryService cloudinaryService;

    private StudentLinksResume getOrCreateProfile(Student student) {

        return repository.findByStudent(student)
                .orElseGet(() -> {
                    StudentLinksResume newProfile = StudentLinksResume.builder()
                            .student(student)
                            .build();
                    return repository.save(newProfile);
                });
    }

    @Override
    public LinksResumeResponse getLinksResume(String email) {

        Student student = studentRepository
                .findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        StudentLinksResume profile = getOrCreateProfile(student);

        return StudentLinksResumeMapper.toResponse(profile);
    }

    @Override
    public LinksResumeResponse updateLinksResume(String email, LinksResumeRequest request) {

        Student student = studentRepository
                .findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        StudentLinksResume profile = getOrCreateProfile(student);

        profile.setLinkedinUrl(request.getLinkedinUrl());
        profile.setGithubUrl(request.getGithubUrl());
        profile.setPortfolioUrl(request.getPortfolioUrl());
        profile.setProfileSummary(request.getProfileSummary());

        repository.save(profile);

        return StudentLinksResumeMapper.toResponse(profile);
    }

    @Override
    public ResumeUploadResponse uploadResume(String email, MultipartFile file) {

        Student student = studentRepository
                .findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        StudentLinksResume profile = getOrCreateProfile(student);

        String resumeUrl = cloudinaryService.uploadFile(file, "student/resume");

        profile.setResumeUrl(resumeUrl);

        repository.save(profile);

        return ResumeUploadResponse.builder()
                .resumeUrl(resumeUrl)
                .build();
    }
}