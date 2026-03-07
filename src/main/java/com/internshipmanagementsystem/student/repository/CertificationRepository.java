package com.internshipmanagementsystem.student.repository;

import com.internshipmanagementsystem.student.model.Certification;
import com.internshipmanagementsystem.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    List<Certification> findByStudent(Student student);

    Optional<Certification> findByIdAndStudent(Long id, Student student);

}