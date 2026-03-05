package com.internshipmanagementsystem.admin.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.internshipmanagementsystem.admin.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByName(String name);
}