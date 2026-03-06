package com.internshipmanagementsystem.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internshipmanagementsystem.admin.model.Coordinator;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

    Optional<Coordinator> findByEmail(String email);

    boolean existsByEmail(String email);
}