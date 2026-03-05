package com.internshipmanagementsystem.admin.dtos;


import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinatorUpdateDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String department;
}