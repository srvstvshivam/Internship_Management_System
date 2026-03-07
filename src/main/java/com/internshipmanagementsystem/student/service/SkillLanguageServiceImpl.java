package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.SkillLanguageRequest;
import com.internshipmanagementsystem.student.dto.SkillLanguageResponse;
import com.internshipmanagementsystem.student.mapper.ProfileSkillLanguageMapper;
import com.internshipmanagementsystem.student.model.ProfileSkillLanguage;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.model.enums.ProficiencyLevel;
import com.internshipmanagementsystem.student.model.enums.SkillLanguageType;
import com.internshipmanagementsystem.student.repository.ProfileSkillLanguageRepository;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillLanguageServiceImpl implements SkillLanguageService {

    private final ProfileSkillLanguageRepository repository;
    private final StudentRepository studentRepository;

    @Override
    public List<SkillLanguageResponse> getAll(String email) {

        Student student = studentRepository.findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return repository.findByStudent(student)
                .stream()
                .map(ProfileSkillLanguageMapper::toResponse)
                .toList();
    }

    @Override
    public SkillLanguageResponse add(String email, SkillLanguageRequest request) {

        Student student = studentRepository.findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        ProfileSkillLanguage skill = ProfileSkillLanguageMapper.toEntity(request, student);

        repository.save(skill);

        return ProfileSkillLanguageMapper.toResponse(skill);
    }

    @Override
    public SkillLanguageResponse update(String email, Long id, SkillLanguageRequest request) {

        ProfileSkillLanguage skill = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill or Language not found"));

        skill.setName(request.getName());

        skill.setProficiency(
                ProficiencyLevel.valueOf(request.getProficiency()));

        skill.setType(
                SkillLanguageType.valueOf(request.getType()));

        repository.save(skill);

        return SkillLanguageResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .type(skill.getType().name())
                .proficiency(skill.getProficiency().name())
                .build();
    }

    @Override
    public void delete(String email, Long id) {

        ProfileSkillLanguage skill = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        if (!skill.getStudent().getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access");
        }

        repository.delete(skill);
    }
}