package com.internshipmanagementsystem.mentor.service;

import com.internshipmanagementsystem.mentor.model.*;
import com.internshipmanagementsystem.mentor.repository.*;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final WeeklyProgressRepository weeklyProgressRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final EvaluationRepository evaluationRepository;
    private final CompletionReportRepository completionReportRepository;

    private Mentor getMentor(Long mentorId) {
        return mentorRepository.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor not found with ID: " + mentorId));
    }

    private Student getStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
    }

    @Override
    @Transactional
    public List<Student> getAssignedStudents(Long mentorId) {
        Mentor mentor = getMentor(mentorId);
        List<Student> students = mentor.getAssignedStudents();
        students.size(); 
        return students;
    }

    @Override
    @Transactional
    public Attendance markAttendance(Long mentorId, Long studentId, LocalDate date, String status, String remarks) {
        Mentor mentor = getMentor(mentorId);
        Student student = getStudent(studentId);

        Attendance attendance = attendanceRepository.findByStudentIdAndAttendanceDate(studentId, date)
                .orElse(new Attendance());

        attendance.setMentor(mentor);
        attendance.setStudent(student);
        attendance.setAttendanceDate(date);
        attendance.setStatus(status);
        attendance.setRemarks(remarks);

        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getStudentAttendance(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @Override
    @Transactional
    public WeeklyProgress addWeeklyProgress(Long mentorId, Long studentId, Integer weekNumber, String taskDescription, String completionStatus, String remarks) {
        Mentor mentor = getMentor(mentorId);
        Student student = getStudent(studentId);

        WeeklyProgress progress = WeeklyProgress.builder()
                .mentor(mentor)
                .student(student)
                .weekNumber(weekNumber)
                .taskDescription(taskDescription)
                .completionStatus(completionStatus)
                .remarks(remarks)
                .build();

        return weeklyProgressRepository.save(progress);
    }

    @Override
    public List<WeeklyProgress> getStudentProgress(Long studentId) {
        return weeklyProgressRepository.findByStudentIdOrderByWeekNumberAsc(studentId);
    }

    @Override
    @Transactional
    public LeaveRequest reviewLeaveRequest(Long mentorId, Long leaveRequestId, String status, String mentorRemarks) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        if (!leaveRequest.getMentor().getId().equals(mentorId)) {
            throw new RuntimeException("Unauthorized: Mentor can only approve their own assigned students' leaves.");
        }

        leaveRequest.setStatus(status);
        leaveRequest.setMentorRemarks(mentorRemarks);

        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public List<LeaveRequest> getPendingLeaveRequests(Long mentorId) {
        return leaveRequestRepository.findByMentorIdAndStatus(mentorId, "PENDING");
    }

    @Override
    @Transactional
    public Evaluation submitEvaluation(Long mentorId, Long studentId, String grade, String remarks, Boolean isVisibleToStudent) {
        Mentor mentor = getMentor(mentorId);
        Student student = getStudent(studentId);

        Evaluation evaluation = evaluationRepository.findByStudentIdAndMentorId(studentId, mentorId)
                .orElse(new Evaluation());

        evaluation.setMentor(mentor);
        evaluation.setStudent(student);
        evaluation.setGrade(grade);
        evaluation.setRemarks(remarks);
        evaluation.setIsVisibleToStudent(isVisibleToStudent != null ? isVisibleToStudent : true);

        return evaluationRepository.save(evaluation);
    }

    @Override
    @Transactional
    public CompletionReport generateCompletionReport(Long mentorId, Long studentId, String reportDetails) {
        Mentor mentor = getMentor(mentorId);
        Student student = getStudent(studentId);

        CompletionReport report = completionReportRepository.findByStudentId(studentId)
                .orElse(new CompletionReport());

        report.setMentor(mentor);
        report.setStudent(student);
        report.setReportDetails(reportDetails);
        
        return completionReportRepository.save(report);
    }

    @Override
    @Transactional
    public CompletionReport submitReportToCoordinator(Long mentorId, Long reportId) {
        CompletionReport report = completionReportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        if (!report.getMentor().getId().equals(mentorId)) {
            throw new RuntimeException("Unauthorized: You can only submit your own reports.");
        }

        report.setSubmittedToCoordinator(true);
        report.setCoordinatorApprovalStatus("PENDING");
        return completionReportRepository.save(report);
    }
}