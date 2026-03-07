package com.internshipmanagementsystem.student.repository;

import com.internshipmanagementsystem.student.model.Education;
import com.internshipmanagementsystem.student.model.EducationDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationDocumentRepository extends JpaRepository<EducationDocument, Long> {

    List<EducationDocument> findByEducation(Education education);

    Optional<EducationDocument> findByIdAndEducation(Long id, Education education);
}