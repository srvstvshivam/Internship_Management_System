package com.internshipmanagementsystem.coordinator.mapper;

import com.internshipmanagementsystem.coordinator.dto.InternshipRequest;
import com.internshipmanagementsystem.coordinator.dto.InternshipResponse;
import com.internshipmanagementsystem.coordinator.model.Internship;
import com.internshipmanagementsystem.coordinator.model.Coordinator;

public class InternshipMapper {

    // =========================
    // REQUEST → ENTITY
    // =========================
    public static Internship toEntity(InternshipRequest request, Coordinator coordinator) {

        return Internship.builder()
                .title(request.getTitle())
                .companyName(request.getCompanyName())
                .description(request.getDescription())
                .location(request.getLocation())
                .sector(request.getSector())
                .durationInMonths(request.getDurationInMonths())
                .stipendAmount(request.getStipendAmount())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .applicationDeadline(request.getApplicationDeadline())
                .eligibleDegrees(request.getEligibleDegrees())
                .eligibleBranches(request.getEligibleBranches())
                .requiredSkills(request.getRequiredSkills())
                .minPassoutYear(request.getMinPassoutYear())
                .maxPassoutYear(request.getMaxPassoutYear())
                .minimumPercentage(request.getMinimumPercentage())
                .noActiveBacklogsRequired(request.getNoActiveBacklogsRequired())
                .maxStudents(request.getMaxStudents())
                .coordinator(coordinator)
                .build();
    }

    // =========================
    // ENTITY → RESPONSE
    // =========================
    public static InternshipResponse toResponse(Internship internship) {

        return InternshipResponse.builder()
                .id(internship.getId())
                .title(internship.getTitle())
                .companyName(internship.getCompanyName())
                .description(internship.getDescription())
                .location(internship.getLocation())
                .sector(internship.getSector())
                .durationInMonths(internship.getDurationInMonths())
                .stipendAmount(internship.getStipendAmount())
                .startDate(internship.getStartDate())
                .endDate(internship.getEndDate())
                .applicationDeadline(internship.getApplicationDeadline())
                .eligibleDegrees(internship.getEligibleDegrees())
                .eligibleBranches(internship.getEligibleBranches())
                .requiredSkills(internship.getRequiredSkills())
                .minPassoutYear(internship.getMinPassoutYear())
                .maxPassoutYear(internship.getMaxPassoutYear())
                .minimumPercentage(internship.getMinimumPercentage())
                .noActiveBacklogsRequired(internship.getNoActiveBacklogsRequired())
                .maxStudents(internship.getMaxStudents())
                .status(internship.getStatus())
                .build();
    }

    // =========================
    // UPDATE EXISTING ENTITY
    // =========================
    public static void updateEntity(Internship internship, InternshipRequest request) {

        internship.setTitle(request.getTitle());
        internship.setCompanyName(request.getCompanyName());
        internship.setDescription(request.getDescription());
        internship.setLocation(request.getLocation());
        internship.setSector(request.getSector());
        internship.setDurationInMonths(request.getDurationInMonths());
        internship.setStipendAmount(request.getStipendAmount());
        internship.setStartDate(request.getStartDate());
        internship.setEndDate(request.getEndDate());
        internship.setApplicationDeadline(request.getApplicationDeadline());
        internship.setEligibleDegrees(request.getEligibleDegrees());
        internship.setEligibleBranches(request.getEligibleBranches());
        internship.setRequiredSkills(request.getRequiredSkills());
        internship.setMinPassoutYear(request.getMinPassoutYear());
        internship.setMaxPassoutYear(request.getMaxPassoutYear());
        internship.setMinimumPercentage(request.getMinimumPercentage());
        internship.setNoActiveBacklogsRequired(request.getNoActiveBacklogsRequired());
        internship.setMaxStudents(request.getMaxStudents());
    }
}