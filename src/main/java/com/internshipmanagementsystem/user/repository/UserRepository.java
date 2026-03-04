package com.internshipmanagementsystem.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internshipmanagementsystem.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailOrMobileNumber(String email, String mobileNumber);

    Optional<User> findByMobileNumber(String mobileNumber);

     Optional<User> findByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);

    boolean existsByEmail(String email);           // 👈 ADD THIS

}