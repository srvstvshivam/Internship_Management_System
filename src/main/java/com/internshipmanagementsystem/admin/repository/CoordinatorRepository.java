package com.internshipmanagementsystem.admin.repository;

import com.internshipmanagementsystem.admin.model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

    Optional<Coordinator> findByEmail(String email);
}