package com.internshipmanagementsystem.user.repository;

import com.internshipmanagementsystem.user.model.PasswordResetToken;
import com.internshipmanagementsystem.user.model.User;
import com.internshipmanagementsystem.user.model.Enums.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, Long> {

   Optional<PasswordResetToken> findByUserAndRole(User user, UserRole role);
void deleteByUserAndRole(User user, UserRole role);


}