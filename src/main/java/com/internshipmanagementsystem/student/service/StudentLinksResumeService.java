package com.internshipmanagementsystem.student.service;

import org.springframework.web.multipart.MultipartFile;

import com.internshipmanagementsystem.student.dto.LinksResumeRequest;
import com.internshipmanagementsystem.student.dto.LinksResumeResponse;
import com.internshipmanagementsystem.student.dto.ResumeUploadResponse;

public interface StudentLinksResumeService {

    LinksResumeResponse getLinksResume(String email);

    LinksResumeResponse updateLinksResume(String email, LinksResumeRequest request);
    ResumeUploadResponse uploadResume(String email, MultipartFile file);
}