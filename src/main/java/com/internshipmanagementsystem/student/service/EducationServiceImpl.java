package com.internshipmanagementsystem.student.service;

import com.internshipmanagementsystem.config.CloudinaryService;
import com.internshipmanagementsystem.student.dto.*;
import com.internshipmanagementsystem.student.mapper.EducationMapper;
import com.internshipmanagementsystem.student.model.Education;
import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.student.repository.EducationRepository;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import com.internshipmanagementsystem.student.model.enums.DocumentType;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.internshipmanagementsystem.user.model.User;
import com.internshipmanagementsystem.user.repository.UserRepository;
import com.internshipmanagementsystem.student.repository.EducationDocumentRepository;
import com.internshipmanagementsystem.student.model.EducationDocument;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {



        private final EducationRepository educationRepository;
        private final StudentRepository studentRepository;
        private final UserRepository userRepository;
        private final EducationDocumentRepository educationDocumentRepository;
private final CloudinaryService cloudinaryService;





   private Student getStudentByEmail(String email) {

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "User not found"));

    return studentRepository.findByUser(user)
            .orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Student not found"));
}
        
        
        @Transactional
        @Override
        public EducationResponse addEducation(String email, EducationRequest request) {

                Student student = getStudentByEmail(email);

                // If new education is marked as current → reset existing current records
                if (Boolean.TRUE.equals(request.getCurrentlyPursuing())) {
                        educationRepository.resetCurrentForStudent(student);
                }

                Education education = EducationMapper.toEntity(request, student);

                Education saved = educationRepository.save(education);

                return EducationMapper.toResponse(saved);
        }

        @Override
        @Transactional
        public EducationListResponse getEducations(String email) {

               Student student = getStudentByEmail(email);
                List<Education> educations = educationRepository.findByStudent(student);

                if (educations.isEmpty()) {
                        return EducationListResponse.builder()
                                        .currentEducation(null)
                                        .previousEducations(List.of())
                                        .build();
                }

                // Step 1: Find current (do NOT modify this variable later)
                Education foundCurrent = educations.stream()
                                .filter(Education::getCurrentlyPursuing)
                                .findFirst()
                                .orElse(null);

                // Step 2: Remove current from others
                List<Education> others = educations.stream()
                                .filter(e -> foundCurrent == null || !e.getId().equals(foundCurrent.getId()))
                                .collect(Collectors.toList());

                // Step 3: Sort others by endYear DESC
                others.sort(
                                Comparator.comparing(Education::getEndYear,
                                                Comparator.nullsLast(Comparator.reverseOrder())));

                // Step 4: Decide final current
                Education finalCurrent = foundCurrent;

                if (finalCurrent == null && !others.isEmpty()) {
                        finalCurrent = others.remove(0);
                }

                return EducationListResponse.builder()
                                .currentEducation(
                                                finalCurrent != null
                                                                ? EducationMapper.toResponse(finalCurrent)
                                                                : null)
                                .previousEducations(
                                                others.stream()
                                                                .map(EducationMapper::toResponse)
                                                                .collect(Collectors.toList()))
                                .build();
        }

        @Transactional
        @Override
        public EducationResponse updateEducation(String email, Long id, EducationRequest request) {

                Student student = getStudentByEmail(email);

                Education education = educationRepository.findByIdAndStudent(id, student)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Education not found"));

                // If marking as current then reset others in single query
                if (Boolean.TRUE.equals(request.getCurrentlyPursuing())) {
                        educationRepository.resetCurrentExcept(student, education.getId());
                }

                
                EducationMapper.updateEntity(education, request);

                return EducationMapper.toResponse(education);
        }

        @Override
        public void deleteEducation(String email, Long id) {

                Student student = getStudentByEmail(email);
                Education education = educationRepository.findByIdAndStudent(id, student)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Education not found"));

                educationRepository.delete(education);
        }
@Override
@Transactional
public EducationDocumentResponse uploadMarksheet(String email, Long educationId, MultipartFile file) {

    Student student = getStudentByEmail(email);

    Education education = educationRepository
            .findByIdAndStudent(educationId, student)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Education not found"));

    String fileUrl = cloudinaryService.uploadFile(file, "student/marksheets");

    EducationDocument document = EducationDocument.builder()
            .education(education)
            .documentType(DocumentType.MARKSHEET)
            .fileUrl(fileUrl)
            .build();

    EducationDocument saved = educationDocumentRepository.save(document);

    return EducationDocumentResponse.builder()
            .id(saved.getId())
            .documentType(saved.getDocumentType().name())
            .fileUrl(saved.getFileUrl())
            .build();
}
@Override
public List<EducationDocumentResponse> getEducationDocuments(String email, Long educationId) {

    Student student = getStudentByEmail(email);

    Education education = educationRepository
            .findByIdAndStudent(educationId, student)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Education not found"));

    return educationDocumentRepository.findByEducation(education)
            .stream()
            .map(doc -> EducationDocumentResponse.builder()
                    .id(doc.getId())
                    .documentType(doc.getDocumentType().name())
                    .fileUrl(doc.getFileUrl())
                    .build())
            .toList();
}
@Override
@Transactional
public void deleteDocument(String email, Long educationId, Long documentId) {

    Student student = getStudentByEmail(email);

    Education education = educationRepository
            .findByIdAndStudent(educationId, student)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Education not found"));

    EducationDocument document = educationDocumentRepository
            .findByIdAndEducation(documentId, education)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Document not found"));

    educationDocumentRepository.delete(document);
}
       
}