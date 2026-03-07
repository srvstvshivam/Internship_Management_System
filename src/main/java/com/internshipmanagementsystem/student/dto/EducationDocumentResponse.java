package com.internshipmanagementsystem.student.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationDocumentResponse {

    private Long id;

    private String documentType;

    private String fileUrl;
}