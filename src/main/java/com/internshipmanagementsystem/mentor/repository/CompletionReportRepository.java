package com.internshipmanagementsystem.mentor.repository;

import com.internshipmanagementsystem.mentor.model.CompletionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompletionReportRepository extends JpaRepository<CompletionReport, Long> {
    Optional<CompletionReport> findByStudentId(Long studentId);
}