package com.internshipmanagementsystem.coordinator.service;

import com.internshipmanagementsystem.coordinator.dto.*;

public interface CoordinatorService {

    CoordinatorLoginResponse login(CoordinatorLoginRequest request);

}