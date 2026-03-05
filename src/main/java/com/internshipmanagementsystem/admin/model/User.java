package com.internshipmanagementsystem.admin.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

import com.internshipmanagementsystem.admin.model.Enums.Role;

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

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;
    private String department;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Student -> Mentor
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private User mentor;

    // Mentor -> Students
    @OneToMany(mappedBy = "mentor")
    private List<User> students = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();
}