package com.internshipmanagementsystem.admin.dtos;

import lombok.Data;

@Data
public class MentorCreateDTO {

    private String name;
    private String email;
    private String password;
    private String technology;

}