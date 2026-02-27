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
        return "Registration Successful";
    }

    public String buildBody() {

        String middle = student.getMiddleName() != null
                ? student.getMiddleName() + " "
                : "";

        String fullName = student.getFirstName() + " "
                + middle
                + student.getLastName();

        return """
                Dear %s,

                Your registration is successful.

                Enrollment Number: %s
                Mobile Number: %s

                Regards,
                Internship Management Team
                """
                .formatted(
                        fullName,
                        student.getEnrollmentNumber(),
                        student.getMobileNumber()
                );
    }
}