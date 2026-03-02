package com.internshipmanagementsystem.coordinator.repository;

import com.internshipmanagementsystem.coordinator.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findByCoordinatorEmail(String email);
}


// package com.internshipmanagementsystem.coordinator.repository;

// import com.internshipmanagementsystem.coordinator.model.Internship;
// import com.internshipmanagementsystem.coordinator.model.enums.InternshipStatus;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

// public interface InternshipRepository extends JpaRepository<Internship, Long> {

//     // Get internships created by a specific coordinator
//     List<Internship> findByCoordinatorId(Long coordinatorId);

//     // Get only OPEN internships (for student side later)
//     List<Internship> findByStatus(InternshipStatus status);

// }