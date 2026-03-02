package com.internshipmanagementsystem.coordinator.repository;

import com.internshipmanagementsystem.coordinator.model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

    Optional<Coordinator> findByEmail(String email);
    // Additional query methods can be defined here as needed

}