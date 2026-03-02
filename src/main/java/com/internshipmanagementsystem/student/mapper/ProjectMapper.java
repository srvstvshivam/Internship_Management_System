package com.internshipmanagementsystem.student.mapper;

import com.internshipmanagementsystem.student.dto.ProjectRequest;
import com.internshipmanagementsystem.student.dto.ProjectResponse;
import com.internshipmanagementsystem.student.model.Project;
import com.internshipmanagementsystem.student.model.Student;

public class ProjectMapper {

    public static Project toEntity(ProjectRequest request, Student student) {
        return Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .githubUrl(request.getGithubUrl())
                .liveUrl(request.getLiveUrl())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .student(student)
                .build();
    }

    public static void updateEntity(Project project, ProjectRequest request) {
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setGithubUrl(request.getGithubUrl());
        project.setLiveUrl(request.getLiveUrl());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
    }

    public static ProjectResponse toResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .githubUrl(project.getGithubUrl())
                .liveUrl(project.getLiveUrl())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .build();
    }
}