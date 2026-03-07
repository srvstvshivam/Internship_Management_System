package com.internshipmanagementsystem.user.model;

import com.internshipmanagementsystem.student.model.Student;
import com.internshipmanagementsystem.user.model.Enums.UserRole;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Universal login ID (STU..., MEN..., COORD..., ADM...)
    @Column(unique = true, nullable = false)
    private String loginId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    // One user -> one student profile
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Student student;
}