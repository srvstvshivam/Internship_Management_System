package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.ProjectRequest;
import com.internshipmanagementsystem.student.dto.ProjectResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse addProject(String email, ProjectRequest request);

    List<ProjectResponse> getProjects(String email);

    ProjectResponse updateProject(String email, Long id, ProjectRequest request);

    void deleteProject(String email, Long id);
}