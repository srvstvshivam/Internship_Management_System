package com.internshipmanagementsystem.mentor.repository;

import com.internshipmanagementsystem.mentor.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findByMentorId(Long mentorId);
    Optional<Attendance> findByStudentIdAndAttendanceDate(Long studentId, LocalDate date);
}