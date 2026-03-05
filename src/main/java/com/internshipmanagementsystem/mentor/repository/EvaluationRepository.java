package com.internshipmanagementsystem.mentor.repository;

import com.internshipmanagementsystem.mentor.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByStudentId(Long studentId);
    Optional<Evaluation> findByStudentIdAndMentorId(Long studentId, Long mentorId);
}