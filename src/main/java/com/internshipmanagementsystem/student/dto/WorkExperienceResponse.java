package com.internshipmanagementsystem.student.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class WorkExperienceResponse {

    private Long id;
    private String companyName;
    private String sector;
    private String jobTitle;
    private String location;
    private String positionType;
    private String jobFunction;
}