package com.internshipmanagementsystem.mentor.repository;

import com.internshipmanagementsystem.mentor.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStudentId(Long studentId);
    List<LeaveRequest> findByMentorId(Long mentorId);
    List<LeaveRequest> findByMentorIdAndStatus(Long mentorId, String status);
}