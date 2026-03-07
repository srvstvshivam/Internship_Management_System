package com.internshipmanagementsystem.student.dto;

import lombok.Data;

@Data
public class SkillLanguageRequest {

    private String name;

    private String type; // SKILL or LANGUAGE

    private String proficiency;
}