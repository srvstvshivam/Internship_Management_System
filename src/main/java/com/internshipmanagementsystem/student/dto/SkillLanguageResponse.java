package com.internshipmanagementsystem.student.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillLanguageResponse {

    private Long id;

    private String name;

    private String type;

    private String proficiency;
}