package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.student.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EducationService {

    // Education CRUD
    EducationResponse addEducation(String email, EducationRequest request);

    EducationResponse updateEducation(String email, Long id, EducationRequest request);

    void deleteEducation(String email, Long id);

    EducationListResponse getEducations(String email);


    // Document APIs
   EducationDocumentResponse uploadMarksheet(String email, Long educationId, MultipartFile file);

    List<EducationDocumentResponse> getEducationDocuments(String email, Long educationId);

    void deleteDocument(String email, Long educationId, Long documentId);
}