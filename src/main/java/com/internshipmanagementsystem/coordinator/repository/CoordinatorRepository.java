package com.internshipmanagementsystem.coordinator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.internshipmanagementsystem.coordinator.model.Coordinator;
import java.util.Optional;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

    Optional<Coordinator> findByEmail(String email);

}