package com.internshipmanagementsystem.student.mapper;

import com.internshipmanagementsystem.student.dto.SkillLanguageRequest;
import com.internshipmanagementsystem.student.dto.SkillLanguageResponse;
import com.internshipmanagementsystem.student.model.ProfileSkillLanguage;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.model.enums.ProficiencyLevel;
import com.internshipmanagementsystem.student.model.enums.SkillLanguageType;




public class ProfileSkillLanguageMapper {

    public static SkillLanguageResponse toResponse(ProfileSkillLanguage skill) {

        return SkillLanguageResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .type(skill.getType().name())
                .proficiency(skill.getProficiency().name())
                .build();
    }

public static ProfileSkillLanguage toEntity(
        SkillLanguageRequest request,
        Student student
) {

    return ProfileSkillLanguage.builder()
            .name(request.getName())
            .type(SkillLanguageType.valueOf(request.getType()))
            .proficiency(ProficiencyLevel.valueOf(request.getProficiency()))
            .student(student)
            .build();
}

}