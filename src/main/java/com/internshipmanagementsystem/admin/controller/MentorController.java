package com.internshipmanagementsystem.admin.controller;

import com.internshipmanagementsystem.admin.dtos.MentorCreateDTO;
import com.internshipmanagementsystem.admin.model.Mentor;
import com.internshipmanagementsystem.admin.service.MentorService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/mentors")
@RequiredArgsConstructor
public class MentorController {

    private final MentorService service;

    // Create mentor
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Mentor create(@RequestBody MentorCreateDTO dto){
        return service.createMentor(dto);
    }

    // Get all mentors
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINATOR')")
    @GetMapping("/all")
    public List<Mentor> getAll(){
        return service.getAllMentors();
    }

    // Activate / Deactivate mentor
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/toggle/{id}")
    public Mentor toggle(@PathVariable Long id){
        return service.toggleActive(id);
    }

}