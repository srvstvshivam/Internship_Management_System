package com.internshipmanagementsystem.student.mapper;

import com.internshipmanagementsystem.student.dto.WorkExperienceRequest;
import com.internshipmanagementsystem.student.dto.WorkExperienceResponse;
import com.internshipmanagementsystem.student.model.WorkExperience;
import com.internshipmanagementsystem.student.model.Student;

public class WorkExperienceMapper {

    public static WorkExperience toEntity(
            WorkExperienceRequest request,
            Student student) {

        return WorkExperience.builder()
                .companyName(request.getCompanyName())
                .sector(request.getSector())
                .jobTitle(request.getJobTitle())
                .location(request.getLocation())
                .positionType(request.getPositionType())
                .jobFunction(request.getJobFunction())
                .student(student)
                .build();
    }

    public static void updateEntity(
            WorkExperience experience,
            WorkExperienceRequest request) {

        experience.setCompanyName(request.getCompanyName());
        experience.setSector(request.getSector());
        experience.setJobTitle(request.getJobTitle());
        experience.setLocation(request.getLocation());
        experience.setPositionType(request.getPositionType());
        experience.setJobFunction(request.getJobFunction());
    }

    public static WorkExperienceResponse toResponse(
            WorkExperience experience) {

        return WorkExperienceResponse.builder()
                .id(experience.getId())
                .companyName(experience.getCompanyName())
                .sector(experience.getSector())
                .jobTitle(experience.getJobTitle())
                .location(experience.getLocation())
                .positionType(experience.getPositionType())
                .jobFunction(experience.getJobFunction())
                .build();
    }
}