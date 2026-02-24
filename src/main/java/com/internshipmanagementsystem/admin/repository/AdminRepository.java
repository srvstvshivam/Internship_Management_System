package com.internshipmanagementsystem.admin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.internshipmanagementsystem.admin.model.Admin;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
