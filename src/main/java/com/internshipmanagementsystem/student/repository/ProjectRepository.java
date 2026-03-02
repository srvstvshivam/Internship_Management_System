package com.internshipmanagementsystem.student.repository;

import com.internshipmanagementsystem.student.model.Project;
import com.internshipmanagementsystem.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByStudent(Student student);

    Optional<Project> findByIdAndStudent(Long id, Student student);
}