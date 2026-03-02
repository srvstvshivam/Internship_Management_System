package com.internshipmanagementsystem.student.dto;
import lombok.Data;



@Data
public class WorkExperienceRequest {

     private String companyName;
    private String sector;
    private String jobTitle;
    private String location;
    private String positionType;
    private String jobFunction;
}