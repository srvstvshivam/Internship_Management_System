package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.*;

import java.util.List;

public interface SkillLanguageService {

    List<SkillLanguageResponse> getAll(String email);

    SkillLanguageResponse add(String email, SkillLanguageRequest request);

    SkillLanguageResponse update(String email, Long id, SkillLanguageRequest request);

    void delete(String email, Long id);
}