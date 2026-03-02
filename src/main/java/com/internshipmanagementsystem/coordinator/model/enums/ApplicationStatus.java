package com.internshipmanagementsystem.coordinator.model.enums;

public enum ApplicationStatus {

    APPLIED,              // Student applied
    UNDER_REVIEW,         // Coordinator reviewing
    SHORTLISTED,          // Selected for next step
    SELECTED,             // Final selection by coordinator

    MENTOR_PENDING,       // Waiting for mentor approval
    MENTOR_ASSIGNED,      // Mentor accepted

    REJECTED,             // Rejected
    WITHDRAWN             // Student cancelled
}