package com.internshipmanagementsystem.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.internshipmanagementsystem.student.model.ProfileSkillLanguage;
import com.internshipmanagementsystem.student.model.Student;

import java.util.List;

public interface ProfileSkillLanguageRepository
        extends JpaRepository<ProfileSkillLanguage, Long> {

    List<ProfileSkillLanguage> findByStudent(Student student);
}