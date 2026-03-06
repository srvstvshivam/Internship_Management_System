package com.internshipmanagementsystem.admin.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentorResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String technology;
    private boolean active;

}