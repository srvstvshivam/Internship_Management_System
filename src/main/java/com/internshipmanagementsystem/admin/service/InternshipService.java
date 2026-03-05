package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.InternshipDTO;

public interface InternshipService {

    InternshipDTO create(InternshipDTO internshipDTO);

    InternshipDTO update(Long id, InternshipDTO internshipDTO);

    void delete(Long id);
}