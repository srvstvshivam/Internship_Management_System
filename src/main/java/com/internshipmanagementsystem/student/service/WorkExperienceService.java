package com.internshipmanagementsystem.student.service;
import com.internshipmanagementsystem.student.dto.WorkExperienceRequest;
import com.internshipmanagementsystem.student.dto.WorkExperienceResponse;
import java.util.List;

public interface WorkExperienceService {

    WorkExperienceResponse addExperience(
            String email,
            WorkExperienceRequest request
    );

    List<WorkExperienceResponse> getExperiences(
            String email
    );

    WorkExperienceResponse updateExperience(
            String email,
            Long id,
            WorkExperienceRequest request
    );

    void deleteExperience(
            String email,
            Long id
    );
}