package com.internshipmanagementsystem.admin.service;



import com.internshipmanagementsystem.admin.dtos.*;

import com.internshipmanagementsystem.admin.mapper.UserMapper;
import com.internshipmanagementsystem.admin.model.User;
import com.internshipmanagementsystem.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.internshipmanagementsystem.student.service.EmailService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // private final EmailService emailService;

    public void createUser(CreateUserRequest request) {

        String generatedPassword = generatePassword();
        String encodedPassword = passwordEncoder.encode(generatedPassword);

        User user = UserMapper.toEntity(request, encodedPassword);

        userRepository.save(user);

        // emailService.sendCredentials(request.getEmail(), generatedPassword);//password should be sent to the user email
    }

    public void updateUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private String generatePassword() {
        return "Temp@" + new Random().nextInt(9999);
    }
}