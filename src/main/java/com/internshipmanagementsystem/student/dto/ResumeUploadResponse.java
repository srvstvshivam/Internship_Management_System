package com.internshipmanagementsystem.student.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeUploadResponse {

    private String resumeUrl;
}