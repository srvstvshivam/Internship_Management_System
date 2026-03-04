package com.internshipmanagementsystem.student.repository;

import com.internshipmanagementsystem.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.internshipmanagementsystem.student.model.Student;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUser_Email(String email);

    boolean existsByUser_Email(String email);

    boolean existsByUser_MobileNumber(String mobileNumber);

    Optional<Student> findByUser(User user);
}