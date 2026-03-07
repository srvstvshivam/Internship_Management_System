package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.CertificationRequest;
import com.internshipmanagementsystem.student.dto.CertificationResponse;
import com.internshipmanagementsystem.student.model.Certification;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.repository.CertificationRepository;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import com.internshipmanagementsystem.student.mapper.CertificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;




@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository repository;
    private final StudentRepository studentRepository;

    @Override
    public List<CertificationResponse> getCertifications(String email) {

        Student student = studentRepository
                .findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return repository.findByStudent(student)
                .stream()
                .map(CertificationMapper::toResponse)
                .toList();
    }

    @Override
    public CertificationResponse addCertification(String email, CertificationRequest request) {

        Student student = studentRepository
                .findByUser_Email(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Certification certification =
                CertificationMapper.toEntity(request, student);

        repository.save(certification);

        return CertificationMapper.toResponse(certification);
    }

    @Override
    public CertificationResponse updateCertification(String email, Long id, CertificationRequest request) {

        Certification certification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found"));

        // Security check
        if (!certification.getStudent().getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access");
        }

        certification.setName(request.getName());
        certification.setIssuingAuthority(request.getIssuingAuthority());
        certification.setLicenseNumber(request.getLicenseNumber());
        certification.setCertificationUrl(request.getCertificationUrl());
        certification.setCertificationDate(request.getCertificationDate());
        certification.setExpiryDate(request.getExpiryDate());
        certification.setDescription(request.getDescription());

        repository.save(certification);

        return CertificationMapper.toResponse(certification);
    }

    @Override
    public void deleteCertification(String email, Long id) {

        Certification certification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found"));

        // Security check
        if (!certification.getStudent().getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access");
        }

        repository.delete(certification);
    }
}