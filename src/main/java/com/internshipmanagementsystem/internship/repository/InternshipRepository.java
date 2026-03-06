package com.internshipmanagementsystem.internship.repository;

import com.internshipmanagementsystem.internship.model.Internship;
import com.internshipmanagementsystem.internship.model.InternshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {

    List<Internship> findByStatus(InternshipStatus status);

    List<Internship> findByCoordinatorId(Long coordinatorId);

    List<Internship> findByDomain(String domain);

    boolean existsByTitle(String title);
}