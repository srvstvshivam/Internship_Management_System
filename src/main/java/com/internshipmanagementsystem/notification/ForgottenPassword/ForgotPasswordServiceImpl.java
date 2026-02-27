package com.internshipmanagementsystem.notification.ForgottenPassword;


import com.internshipmanagementsystem.notification.EmailService;
import com.internshipmanagementsystem.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import com.internshipmanagementsystem.student.model.Student;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final PasswordResetTokenRepository tokenRepository;
    private final StudentRepository studentRepository;
    // private final MentorRepository mentorRepository;
    // private final CoordinatorRepository coordinatorRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private String generateOtp() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    @Override
    public void sendOtp(String email, String role) {

        boolean exists = switch (role.toUpperCase()) {
            case "STUDENT" -> studentRepository.findByEmail(email).isPresent();
            // case "MENTOR" -> mentorRepository.findByEmail(email).isPresent();
            // case "COORDINATOR" -> coordinatorRepository.findByEmail(email).isPresent();
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role");
        };

        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found");
        }

        String otp = generateOtp();

        tokenRepository.deleteByEmailAndRole(email, role);

        PasswordResetToken token = PasswordResetToken.builder()
                .email(email)
                .role(role)
                .otp(otp)
                .expiryTime(LocalDateTime.now().plusMinutes(10))
                .build();

        tokenRepository.save(token);

        String subject = "Password Reset OTP";

        String body = """
                Your OTP for password reset is: %s

                Valid for 10 minutes.
                """.formatted(otp);

        emailService.sendEmail(email, subject, body);
    }

    @Override
    public void resetPassword(String email,
                              String role,
                              String otp,
                              String newPassword) {

        PasswordResetToken token = tokenRepository
                .findByEmailAndRole(email, role)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request"));

        if (!token.getOtp().equals(otp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP");
        }

        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP expired");
        }

        switch (role.toUpperCase()) {
            case "STUDENT" -> {
                Student student = studentRepository.findByEmail(email).get();
                student.setPassword(passwordEncoder.encode(newPassword));
                studentRepository.save(student);
            }
            case "MENTOR" -> {
                // Mentor mentor = mentorRepository.findByEmail(email).get();
                // mentor.setPassword(passwordEncoder.encode(newPassword));
                // mentorRepository.save(mentor);
            }
            case "COORDINATOR" -> {
                // Coordinator coordinator = coordinatorRepository.findByEmail(email).get();
                // coordinator.setPassword(passwordEncoder.encode(newPassword));
                // coordinatorRepository.save(coordinator);
            }
        }

        tokenRepository.deleteByEmailAndRole(email, role);
    }
}
