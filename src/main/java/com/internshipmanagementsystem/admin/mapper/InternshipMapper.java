package com.internshipmanagementsystem.admin.mapper;

import com.internshipmanagementsystem.admin.dtos.InternshipDTO;
import com.internshipmanagementsystem.admin.model.Internship;

public class InternshipMapper {

    public static InternshipDTO toDTO(Internship internship) {
        if (internship == null) return null;

        return InternshipDTO.builder()
                .id(internship.getId())
                .title(internship.getTitle())
                .description(internship.getDescription())
                .companyName(internship.getCompanyName())
                .location(internship.getLocation())
                .startDate(internship.getStartDate())
                .endDate(internship.getEndDate())
                .build();
    }

    public static Internship toEntity(InternshipDTO dto) {
        if (dto == null) return null;

        Internship internship = new Internship();
        internship.setTitle(dto.getTitle());
        internship.setDescription(dto.getDescription());
        internship.setCompanyName(dto.getCompanyName());
        internship.setLocation(dto.getLocation());
        internship.setStartDate(dto.getStartDate());
        internship.setEndDate(dto.getEndDate());
        return internship;
    }
}