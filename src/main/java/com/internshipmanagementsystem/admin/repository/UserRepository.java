package com.internshipmanagementsystem.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internshipmanagementsystem.admin.model.User;
import com.internshipmanagementsystem.admin.model.Enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    long countByRole(Role role);
}