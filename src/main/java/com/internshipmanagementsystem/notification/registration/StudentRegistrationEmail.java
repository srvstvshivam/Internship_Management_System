package com.internshipmanagementsystem.notification.registration;

import com.internshipmanagementsystem.student.dto.StudentResponse;

public class StudentRegistrationEmail {

    private final StudentResponse student;

    public StudentRegistrationEmail(StudentResponse student) {
        this.student = student;
    }

    public String getEmail() {
        return student.getEmail();
    }

    public String getSubject() {
        return "Registration Confirmed - Internship Management System";
    }

    public String buildBody() {

        String middle = (student.getMiddleName() != null && !student.getMiddleName().isBlank())
                ? student.getMiddleName() + " "
                : "";

        String fullName = student.getFirstName() + " " + middle + student.getLastName();

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <style>
                        body { font-family: 'Segoe UI', Helvetica, Arial, sans-serif; line-height: 1.6; color: #333333; margin: 0; padding: 0; }
                        .container { max-width: 600px; margin: 20px auto; border: 1px solid #e0e0e0; border-radius: 8px; overflow: hidden; }
                        .header { background-color: #1a73e8; padding: 30px; text-align: center; color: #ffffff; }
                        .content { padding: 40px; background-color: #ffffff; }
                        .footer { background-color: #f8f9fa; padding: 20px; text-align: center; font-size: 12px; color: #70757a; border-top: 1px solid #e0e0e0; }
                        .info-box { background-color: #f1f3f4; border-radius: 6px; padding: 20px; margin: 25px 0; }
                        .info-item { margin-bottom: 10px; font-size: 14px; }
                        .btn { display: inline-block; padding: 12px 30px; color: #ffffff !important; background-color: #1a73e8;
                               text-decoration: none; border-radius: 4px; font-weight: 600; margin-top: 20px; }
                        .label { color: #5f6368; font-weight: bold; width: 160px; display: inline-block; }
                    </style>
                </head>
                <body>
                    <div class="container">

                        <div class="header">
                            <h1 style="margin:0; font-size: 24px;">Registration Successful</h1>
                        </div>

                        <div class="content">

                            <p style="font-size: 16px;">Dear <strong>%s</strong>,</p>

                            <p>
                                Welcome to the <strong>Internship Management System</strong>.
                                Your account has been successfully created and is now active.
                            </p>

                            <div class="info-box">

                                <div class="info-item">
                                    <span class="label">Candidate ID (Login ID):</span> %s
                                </div>

                                <div class="info-item">
                                    <span class="label">Email:</span> %s
                                </div>

                                <div class="info-item">
                                    <span class="label">Mobile Number:</span> %s
                                </div>

                            </div>

                            <p>
                                You can login to the portal using your <strong>Candidate ID, Email, or Mobile Number</strong>
                                along with your password.
                            </p>

                            <div style="text-align: center;">
                                <a href="http://localhost:8082/login" class="btn">Access Student Portal</a>
                            </div>

                        </div>

                        <div class="footer">
                            <p>This is an automated message, please do not reply directly to this email.</p>
                            <p>&copy; 2026 Internship Management System | All Rights Reserved</p>
                        </div>

                    </div>
                </body>
                </html>
                """.formatted(
                fullName,
                student.getEnrollmentNumber(), // Candidate ID
                student.getEmail(),
                student.getMobileNumber()
        );
    }
}