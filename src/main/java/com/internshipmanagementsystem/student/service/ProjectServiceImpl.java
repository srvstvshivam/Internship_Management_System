package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.ProjectRequest;
import com.internshipmanagementsystem.student.dto.ProjectResponse;
import com.internshipmanagementsystem.student.mapper.ProjectMapper;
import com.internshipmanagementsystem.student.model.Project;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.repository.ProjectRepository;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import com.internshipmanagementsystem.user.model.User;
import com.internshipmanagementsystem.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    private Student getStudentByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"));

        return studentRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"));
    }

    @Transactional
    @Override
    public ProjectResponse addProject(String email, ProjectRequest request) {

        Student student = getStudentByEmail(email);

        Project project = ProjectMapper.toEntity(request, student);

        return ProjectMapper.toResponse(projectRepository.save(project));
    }

    @Override
    public List<ProjectResponse> getProjects(String email) {

        Student student = getStudentByEmail(email);

        return projectRepository.findByStudent(student)
                .stream()
                .map(ProjectMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProjectResponse updateProject(String email, Long id, ProjectRequest request) {

        Student student = getStudentByEmail(email);

        Project project = projectRepository.findByIdAndStudent(id, student)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Project not found"));

        ProjectMapper.updateEntity(project, request);

        return ProjectMapper.toResponse(project);
    }

    @Transactional
    @Override
    public void deleteProject(String email, Long id) {

        Student student = getStudentByEmail(email);

        Project project = projectRepository.findByIdAndStudent(id, student)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Project not found"));

        projectRepository.delete(project);
    }
}