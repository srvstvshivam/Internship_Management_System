package com.internshipmanagementsystem.student.model;

import jakarta.persistence.*;
import lombok.*;
import com.internshipmanagementsystem.student.model.enums.ProficiencyLevel;
import com.internshipmanagementsystem.student.model.enums.SkillLanguageType;

@Entity
@Table(name = "skills_languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileSkillLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SkillLanguageType type;

    @Enumerated(EnumType.STRING)
    private ProficiencyLevel proficiency;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}