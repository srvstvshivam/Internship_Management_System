package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.CertificationRequest;
import com.internshipmanagementsystem.student.dto.CertificationResponse;
import java.util.List;



public interface CertificationService {

    List<CertificationResponse> getCertifications(String email);

    CertificationResponse addCertification(String email, CertificationRequest request);

    CertificationResponse updateCertification(String email, Long id, CertificationRequest request);

    void deleteCertification(String email, Long id);
}