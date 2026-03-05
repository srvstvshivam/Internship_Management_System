package com.internshipmanagementsystem.admin.service;


import com.internshipmanagementsystem.admin.dtos.*;

public interface CoordinatorService {

    CoordinatorResponseDTO registerCoordinator(CoordinatorRegisterDTO dto);

    CoordinatorResponseDTO updateCoordinator(Long id, CoordinatorUpdateDTO dto);
}
