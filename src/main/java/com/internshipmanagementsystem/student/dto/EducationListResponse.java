package com.internshipmanagementsystem.student.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EducationListResponse {

    private EducationResponse currentEducation;

    private List<EducationResponse> previousEducations;
}