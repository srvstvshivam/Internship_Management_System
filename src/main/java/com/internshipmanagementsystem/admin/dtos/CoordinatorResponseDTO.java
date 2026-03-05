package com.internshipmanagementsystem.admin.dtos;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinatorResponseDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private String password;
}