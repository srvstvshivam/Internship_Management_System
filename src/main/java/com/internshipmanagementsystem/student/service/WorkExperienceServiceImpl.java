package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.WorkExperienceRequest;
import com.internshipmanagementsystem.student.dto.WorkExperienceResponse;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.model.WorkExperience;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import com.internshipmanagementsystem.student.repository.WorkExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import com.internshipmanagementsystem.student.mapper.WorkExperienceMapper;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl
        implements WorkExperienceService {

    private final WorkExperienceRepository repository;
    private final StudentRepository studentRepository;

    @Transactional
    @Override
    public WorkExperienceResponse addExperience(
            String email,
            WorkExperienceRequest request) {

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"));

        WorkExperience experience = WorkExperienceMapper.toEntity(request, student);

        return WorkExperienceMapper.toResponse(
                repository.save(experience));
    }

    @Override
    public List<WorkExperienceResponse> getExperiences(
            String email) {

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"));

        return repository.findByStudent(student)
                .stream()
                .map(WorkExperienceMapper::toResponse)
                .toList();
    }

    @Transactional
    @Override
    public WorkExperienceResponse updateExperience(
            String email,
            Long id,
            WorkExperienceRequest request) {

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"));

        WorkExperience experience = repository.findByIdAndStudent(id, student)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Experience not found"));

        WorkExperienceMapper.updateEntity(experience, request);

        return WorkExperienceMapper.toResponse(experience);
    }

    @Transactional
    @Override
    public void deleteExperience(
            String email,
            Long id) {

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"));

        WorkExperience experience = repository.findByIdAndStudent(id, student)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Experience not found"));

        repository.delete(experience);
    }
}