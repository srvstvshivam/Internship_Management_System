package com.internshipmanagementsystem.admin.repository;


import com.internshipmanagementsystem.admin.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InternshipRepository extends JpaRepository<Internship, Long> {}