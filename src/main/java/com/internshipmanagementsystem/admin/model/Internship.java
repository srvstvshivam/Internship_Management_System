package com.internshipmanagementsystem.admin.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.internshipmanagementsystem.admin.model.Enums.Role;
import java.util.Set;
import java.util.HashSet;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private String stipend;
}