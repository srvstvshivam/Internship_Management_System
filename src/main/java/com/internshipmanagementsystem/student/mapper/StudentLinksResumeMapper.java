package com.internshipmanagementsystem.student.mapper;

import com.internshipmanagementsystem.student.dto.LinksResumeResponse;
import com.internshipmanagementsystem.student.model.StudentLinksResume;

public class StudentLinksResumeMapper {

    public static LinksResumeResponse toResponse(StudentLinksResume profile) {

        return LinksResumeResponse.builder()
                
                .linkedinUrl(profile.getLinkedinUrl())
                .githubUrl(profile.getGithubUrl())
                .portfolioUrl(profile.getPortfolioUrl())
                .profileSummary(profile.getProfileSummary())
                .build();
    }
}