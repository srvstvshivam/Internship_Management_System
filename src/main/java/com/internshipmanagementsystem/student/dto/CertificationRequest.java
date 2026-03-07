package com.internshipmanagementsystem.student.dto;

import lombok.Data;
import java.time.LocalDate;


@Data
public class CertificationRequest {

    private String name;

    private String issuingAuthority;

    private String licenseNumber;

    private String certificationUrl;

    private LocalDate certificationDate;

    private LocalDate expiryDate;

    private String description;
}