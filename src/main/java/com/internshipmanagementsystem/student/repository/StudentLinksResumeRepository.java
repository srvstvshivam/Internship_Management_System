package com.internshipmanagementsystem.student.repository;

import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.model.StudentLinksResume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentLinksResumeRepository extends JpaRepository<StudentLinksResume, Long> {

    Optional<StudentLinksResume> findByStudent(Student student);

    
}