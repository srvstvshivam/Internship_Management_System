package com.internshipmanagementsystem.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
     @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("IMS Password Reset OTP");

        String html = """
            <div style="font-family:Arial;padding:20px">
                <h2 style="color:#2c3e50">Internship Management System</h2>
                <p>Your OTP for password reset:</p>

                <div style="
                    font-size:28px;
                    font-weight:bold;
                    background:#007bff;
                    color:white;
                    padding:15px;
                    text-align:center;
                    width:200px;
                    border-radius:8px;
                    margin:auto;">
                    """ + otp + """
                </div>

                <p>This OTP is valid for <b>10 minutes</b>.</p>
            </div>
        """;

        helper.setText(html, true);
        mailSender.send(message);
    }
}
