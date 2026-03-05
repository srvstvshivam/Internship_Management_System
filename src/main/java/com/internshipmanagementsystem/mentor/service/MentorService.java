package com.internshipmanagementsystem.mentor.service;

import com.internshipmanagementsystem.mentor.model.*;
import com.internshipmanagementsystem.student.model.Student;

import java.time.LocalDate;
import java.util.List;

public interface MentorService {
    
    // View Assigned Students
    List<Student> getAssignedStudents(Long mentorId);

    // Attendance Management
    Attendance markAttendance(Long mentorId, Long studentId, LocalDate date, String status, String remarks);
    List<Attendance> getStudentAttendance(Long studentId);

    // Progress Tracking
    WeeklyProgress addWeeklyProgress(Long mentorId, Long studentId, Integer weekNumber, String taskDescription, String completionStatus, String remarks);
    List<WeeklyProgress> getStudentProgress(Long studentId);

    // Leave Management
    LeaveRequest reviewLeaveRequest(Long mentorId, Long leaveRequestId, String status, String mentorRemarks);
    List<LeaveRequest> getPendingLeaveRequests(Long mentorId);

    // Feedback and Evaluation
    Evaluation submitEvaluation(Long mentorId, Long studentId, String grade, String remarks, Boolean isVisibleToStudent);
    
    // Internship Completion Report
    CompletionReport generateCompletionReport(Long mentorId, Long studentId, String reportDetails);
    CompletionReport submitReportToCoordinator(Long mentorId, Long reportId);
}