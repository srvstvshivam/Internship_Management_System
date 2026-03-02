package com.internshipmanagementsystem.student.repository;

import com.internshipmanagementsystem.student.model.WorkExperience;
import com.internshipmanagementsystem.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkExperienceRepository
        extends JpaRepository<WorkExperience, Long> {

    List<WorkExperience> findByStudent(Student student);

    Optional<WorkExperience> findByIdAndStudent(
            Long id,
            Student student
    );
}