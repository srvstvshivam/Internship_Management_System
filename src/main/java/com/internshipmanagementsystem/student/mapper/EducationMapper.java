package com.internshipmanagementsystem.student.mapper;

import com.internshipmanagementsystem.student.dto.EducationRequest;
import com.internshipmanagementsystem.student.dto.EducationResponse;
import com.internshipmanagementsystem.student.model.Education;
import com.internshipmanagementsystem.student.model.Student;

public class EducationMapper {

    public static EducationResponse toResponse(Education education) {
        return EducationResponse.builder()
                .id(education.getId())
                .educationLevel(education.getEducationLevel())
                .institutionName(education.getInstitutionName())
                .educationType(education.getEducationType())
                .boardOrUniversity(education.getBoardOrUniversity())
                .specialization(education.getSpecialization())
                .percentageOrCgpa(education.getPercentageOrCgpa())
                .gradingType(education.getGradingType())
                .marksheetUrl(education.getMarksheetUrl())
                .startYear(education.getStartYear())
                .endYear(education.getEndYear())
                .currentlyPursuing(education.getCurrentlyPursuing())
                .currentSemester(education.getCurrentSemester())
                .build();
    }
    public static Education toEntity(EducationRequest request, Student student) {

    return Education.builder()
            .educationLevel(request.getEducationLevel())
            .institutionName(request.getInstitutionName())
            .educationType(request.getEducationType())
            .boardOrUniversity(request.getBoardOrUniversity())
            .specialization(request.getSpecialization())
            .percentageOrCgpa(request.getPercentageOrCgpa())
            .gradingType(request.getGradingType())
            .startYear(request.getStartYear())
            .endYear(request.getEndYear())
            .currentlyPursuing(request.getCurrentlyPursuing())
            .currentSemester(request.getCurrentSemester())
            .student(student)
            .build();
}
public static void updateEntity(Education education, EducationRequest request) {

    education.setEducationLevel(request.getEducationLevel());
    education.setInstitutionName(request.getInstitutionName());
    education.setEducationType(request.getEducationType());
    education.setBoardOrUniversity(request.getBoardOrUniversity());
    education.setSpecialization(request.getSpecialization());
    education.setPercentageOrCgpa(request.getPercentageOrCgpa());
    education.setGradingType(request.getGradingType());
    education.setStartYear(request.getStartYear());
    education.setEndYear(request.getEndYear());
    education.setCurrentlyPursuing(request.getCurrentlyPursuing());
    education.setCurrentSemester(request.getCurrentSemester());
}
}