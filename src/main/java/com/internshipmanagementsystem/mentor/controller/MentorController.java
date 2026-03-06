package com.internshipmanagementsystem.mentor.controller;

import com.internshipmanagementsystem.mentor.dto.*;
import com.internshipmanagementsystem.mentor.model.*;
import com.internshipmanagementsystem.mentor.service.MentorService;
import com.internshipmanagementsystem.student.model.Student;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentors/{mentorId}")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MentorController {

    private final MentorService mentorService;

    // 1. View Assigned Students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAssignedStudents(@PathVariable Long mentorId) {
        return ResponseEntity.ok(mentorService.getAssignedStudents(mentorId));
    }

    // 2. Attendance Management
    @PostMapping("/attendance")
    public ResponseEntity<Attendance> markAttendance(
            @PathVariable Long mentorId,
            @Valid @RequestBody AttendanceRequest request) {
        Attendance attendance = mentorService.markAttendance(
                mentorId, request.getStudentId(), request.getDate(),
                request.getStatus(), request.getRemarks());
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/students/{studentId}/attendance")
    public ResponseEntity<List<Attendance>> getStudentAttendance(@PathVariable Long mentorId, @PathVariable Long studentId) {
        return ResponseEntity.ok(mentorService.getStudentAttendance(studentId));
    }

    // 3. Progress Tracking
    @PostMapping("/progress")
    public ResponseEntity<WeeklyProgress> addWeeklyProgress(
            @PathVariable Long mentorId,
            @Valid @RequestBody WeeklyProgressRequest request) {
        WeeklyProgress progress = mentorService.addWeeklyProgress(
                mentorId, request.getStudentId(), request.getWeekNumber(),
                request.getTaskDescription(), request.getCompletionStatus(), request.getRemarks());
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/students/{studentId}/progress")
    public ResponseEntity<List<WeeklyProgress>> getStudentProgress(@PathVariable Long mentorId, @PathVariable Long studentId) {
        return ResponseEntity.ok(mentorService.getStudentProgress(studentId));
    }

    // 4. Leave Management
    @GetMapping("/leaves/pending")
    public ResponseEntity<List<LeaveRequest>> getPendingLeaves(@PathVariable Long mentorId) {
        return ResponseEntity.ok(mentorService.getPendingLeaveRequests(mentorId));
    }

    @PutMapping("/leaves/{leaveId}/review")
    public ResponseEntity<LeaveRequest> reviewLeave(
            @PathVariable Long mentorId,
            @PathVariable Long leaveId,
            @Valid @RequestBody LeaveReviewRequest request) {
        return ResponseEntity.ok(mentorService.reviewLeaveRequest(
                mentorId, leaveId, request.getStatus(), request.getMentorRemarks()));
    }

    // 5. Feedback and Evaluation
    @PostMapping("/evaluation")
    public ResponseEntity<Evaluation> submitEvaluation(
            @PathVariable Long mentorId,
            @Valid @RequestBody EvaluationRequest request) {
        Evaluation evaluation = mentorService.submitEvaluation(
                mentorId, request.getStudentId(), request.getGrade(),
                request.getRemarks(), request.getIsVisibleToStudent());
        return ResponseEntity.ok(evaluation);
    }

    // 6. Internship Completion Report
    @PostMapping("/reports")
    public ResponseEntity<CompletionReport> generateReport(
            @PathVariable Long mentorId,
            @Valid @RequestBody CompletionReportRequest request) {
        CompletionReport report = mentorService.generateCompletionReport(
                mentorId, request.getStudentId(), request.getReportDetails());
        return ResponseEntity.ok(report);
    }

    @PostMapping("/reports/{reportId}/submit")
    public ResponseEntity<CompletionReport> submitReportToCoordinator(
            @PathVariable Long mentorId,
            @PathVariable Long reportId) {
        return ResponseEntity.ok(mentorService.submitReportToCoordinator(mentorId, reportId));
    }
}