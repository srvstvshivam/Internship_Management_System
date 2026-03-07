package com.internshipmanagementsystem.student.mapper;

import com.internshipmanagementsystem.student.dto.CertificationRequest;
import com.internshipmanagementsystem.student.dto.CertificationResponse;
import com.internshipmanagementsystem.student.model.Certification;
import com.internshipmanagementsystem.student.model.Student;

public class CertificationMapper {

    public static Certification toEntity(CertificationRequest request, Student student) {

        return Certification.builder()
                .name(request.getName())
                .issuingAuthority(request.getIssuingAuthority())
                .licenseNumber(request.getLicenseNumber())
                .certificationUrl(request.getCertificationUrl())
                .certificationDate(request.getCertificationDate())
                .expiryDate(request.getExpiryDate())
                .description(request.getDescription())
                .student(student)
                .build();
    }

    public static CertificationResponse toResponse(Certification certification) {

        return CertificationResponse.builder()
                .id(certification.getId())
                .name(certification.getName())
                .issuingAuthority(certification.getIssuingAuthority())
                .licenseNumber(certification.getLicenseNumber())
                .certificationUrl(certification.getCertificationUrl())
                .certificationDate(certification.getCertificationDate())
                .expiryDate(certification.getExpiryDate())
                .description(certification.getDescription())
                .build();
    }
}