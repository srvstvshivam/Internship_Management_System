package com.internshipmanagementsystem.coordinator.service;

import com.internshipmanagementsystem.coordinator.dto.CoordinatorLoginRequest;
import com.internshipmanagementsystem.coordinator.dto.CoordinatorLoginResponse;

public interface CoordinatorService {
    CoordinatorLoginResponse login(CoordinatorLoginRequest request);
}