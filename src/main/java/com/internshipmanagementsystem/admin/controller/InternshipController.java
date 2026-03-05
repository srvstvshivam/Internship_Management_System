package com.internshipmanagementsystem.admin.controller;

import com.internshipmanagementsystem.admin.model.Internship;
import com.internshipmanagementsystem.admin.dtos.InternshipDTO;
import com.internshipmanagementsystem.admin.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService service;

    @PostMapping("/create")
    public ResponseEntity<InternshipDTO> create(@RequestBody InternshipDTO internship){
        InternshipDTO created = service.create(internship);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InternshipDTO> update(@PathVariable Long id,
                                                @RequestBody InternshipDTO internship){
        InternshipDTO updated = service.update(id, internship);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean deleted = service.delete(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}