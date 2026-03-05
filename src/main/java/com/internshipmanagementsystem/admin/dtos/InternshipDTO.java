package com.internshipmanagementsystem.admin.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipDTO {

    private Long id;

    private String title;

    private String description;

    private String companyName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String location;

    private String stipend;
}