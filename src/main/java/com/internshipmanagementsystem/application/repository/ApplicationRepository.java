package com.internshipmanagementsystem.application.repository;

import com.internshipmanagementsystem.application.model.Application;
import com.internshipmanagementsystem.application.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    
    List<Application> findByInternshipId(Long internshipId);

    
    List<Application> findByStatus(ApplicationStatus status);

   
    List<Application> findByInternshipIdAndStatus(Long internshipId, ApplicationStatus status);

   
    List<Application> findByStudentId(Long studentId);

   
    boolean existsByStudentIdAndInternshipId(Long studentId, Long internshipId);

   
    long countByInternshipId(Long internshipId);

    
    long countByInternshipIdAndStatus(Long internshipId, ApplicationStatus status);

    
    Optional<Application> findByStudentIdAndInternshipId(Long studentId, Long internshipId);
}