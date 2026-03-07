package com.internshipmanagementsystem.student.model;

import jakarta.persistence.*;
import lombok.*;
import com.internshipmanagementsystem.student.model.enums.DocumentType;

@Entity
@Table(name = "education_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_id")
    private Education education;
}