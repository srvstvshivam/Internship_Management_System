package com.internshipmanagementsystem.admin.service;

import com.internshipmanagementsystem.admin.dtos.MentorCreateDTO;
import com.internshipmanagementsystem.admin.model.Mentor;
import com.internshipmanagementsystem.admin.repository.MentorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository repo;
    private final PasswordEncoder encoder;

    // Create Mentor
    public Mentor createMentor(MentorCreateDTO dto){

        // check if email already exists
        if(repo.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("Mentor with this email already exists");
        }

        Mentor mentor = Mentor.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .technology(dto.getTechnology())
                .active(true)
                .role("MENTOR")
                .build();

        return repo.save(mentor);
    }

    // Get All Mentors
    public List<Mentor> getAllMentors(){
        return repo.findAll();
    }

    // Toggle Active Status
    public Mentor toggleActive(Long id){

        Mentor mentor = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor not found with id: " + id));

        mentor.setActive(!mentor.isActive());

        return repo.save(mentor);
    }

    // Find Mentor By Email
    public Mentor findByEmail(String email){
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Mentor not found with email: " + email));
    }

}