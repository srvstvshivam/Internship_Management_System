package com.internshipmanagementsystem.coordinator.model.enums;


public enum InternshipStatus {

    DRAFT,                // Created but not published
    PENDING_APPROVAL,     // Sent to mentor for approval
    OPEN,                 // Open for applications
    CLOSED,               // Application deadline over
    FILLED,               // Max students reached
    IN_PROGRESS,          // Internship started
    COMPLETED,            // Internship finished
    CANCELLED             // Cancelled by coordinator
}