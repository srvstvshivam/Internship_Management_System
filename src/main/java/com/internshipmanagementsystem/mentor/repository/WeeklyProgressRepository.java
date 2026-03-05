package com.internshipmanagementsystem.mentor.repository;

import com.internshipmanagementsystem.mentor.model.WeeklyProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeklyProgressRepository extends JpaRepository<WeeklyProgress, Long> {
    List<WeeklyProgress> findByStudentId(Long studentId);
    List<WeeklyProgress> findByMentorId(Long mentorId);
    List<WeeklyProgress> findByStudentIdOrderByWeekNumberAsc(Long studentId);
}