package com.internshipmanagementsystem.student.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinksResumeResponse {

    private String linkedinUrl;

    private String githubUrl;

    private String portfolioUrl;

    private String profileSummary;

}