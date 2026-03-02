package com.internshipmanagementsystem.coordinator.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CoordinatorLoginResponse {

    private Long id;
     private String firstName;
    private String middleName;
    private String lastName;
    private String token;
    private String email;
    private String role;
        private String message;
 
}