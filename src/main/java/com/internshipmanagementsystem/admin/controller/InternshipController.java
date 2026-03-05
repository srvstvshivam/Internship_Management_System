package com.internshipmanagementsystem.admin.controller;

import com.internshipmanagementsystem.admin.dtos.InternshipDTO;
import com.internshipmanagementsystem.admin.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService service;

    @PostMapping("/create")
    public InternshipDTO create(@RequestBody InternshipDTO internshipDTO) {

        return service.create(internshipDTO);
    }

    @PutMapping("/update/{id}")
    public InternshipDTO update(@PathVariable Long id,
                                @RequestBody InternshipDTO internshipDTO) {

        return service.update(id, internshipDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}