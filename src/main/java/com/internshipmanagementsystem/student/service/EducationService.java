package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.*;

public interface EducationService {

    EducationResponse addEducation(String email, EducationRequest request);

    EducationResponse updateEducation(String email, Long id, EducationRequest request);

    void deleteEducation(String email, Long id);

    EducationListResponse getEducations(String email);
}