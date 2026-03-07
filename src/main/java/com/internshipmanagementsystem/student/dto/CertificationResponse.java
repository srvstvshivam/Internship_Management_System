package com.internshipmanagementsystem.student.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class CertificationResponse {

    private Long id;

    private String name;

    private String issuingAuthority;

    private String licenseNumber;

    private String certificationUrl;

    private LocalDate certificationDate;

    private LocalDate expiryDate;

    private String description;
}