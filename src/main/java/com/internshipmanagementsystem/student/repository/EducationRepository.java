package com.internshipmanagementsystem.student.repository;

import com.internshipmanagementsystem.student.model.Education;
import com.internshipmanagementsystem.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long> {

    // Get all educations of a student
    List<Education> findByStudent(Student student);

    // Get specific education of a student (ownership validation)
    Optional<Education> findByIdAndStudent(Long id, Student student);

    // Check if student already has a current education
    boolean existsByStudentAndCurrentlyPursuingTrue(Student student);

    @Modifying
    @Query("UPDATE Education e SET e.currentlyPursuing = false WHERE e.student = :student AND e.id <> :excludeId")
    void resetCurrentExcept(@Param("student") Student student,
            @Param("excludeId") Long excludeId);



    @Modifying
@Query("UPDATE Education e SET e.currentlyPursuing = false WHERE e.student = :student")
void resetCurrentForStudent(@Param("student") Student student);
}