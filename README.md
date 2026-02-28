# Internship Management System (IMS)

Internship Management System (IMS) is a web-based application developed to automate and manage the complete internship lifecycle under the Work-Based Learning (WBL) Program at C-DAC Delhi (2025–2026), E-Governance Domain.

The system provides a centralized platform to manage internship scheduling, application processing, mentor assignment, attendance tracking, evaluation, and certificate generation through a structured role-based workflow.

Backend Live URL:
https://internship-management-system-2c4j.onrender.com

------------------------------------------------------------

## 1. Project Objective

The objective of this project is to replace manual internship management processes such as spreadsheet tracking and email-based approvals with a secure, structured, and workflow-driven system.

The system ensures:
- Centralized internship data management
- Role-based access control
- Secure authentication using JWT
- Transparent approval workflows
- Automated certificate generation
- Historical internship record storage

------------------------------------------------------------

## 2. System Architecture

The application follows a Three-Tier Architecture:

### Presentation Layer (Frontend)
- Developed using React.js
- Role-based dashboards
- REST API communication
- JWT token handling

### Application Layer (Backend)
- Developed using Spring Boot
- RESTful API architecture
- Business logic processing
- JWT-based authentication and authorization
- Internship lifecycle management
- Certificate generation module

### Data Layer (Database)
- PostgreSQL (Relational Database)
- Spring Data JPA / Hibernate ORM
- Structured relational schema
- ACID-compliant transactions
- Secure database connection

------------------------------------------------------------

## 3. User Roles and Functional Modules

### Student
- Registration and Login
- View and Apply for Internship Programs
- Upload Required Documents
- Track Application Status
- View Assigned Mentor and Schedule
- View Attendance and Progress
- Download Internship Certificate
- Access Historical Internship Records

### Coordinator
- Internship Schedule Management
- Internship Creation and Posting
- Application Review and Status Update
- Document Verification
- Mentor Assignment Workflow
- Internship Completion Approval
- Certificate Generation Trigger
- Report Viewing

### Mentor
- Internship Schedule Approval
- Mentor Assignment Approval
- Attendance Management
- Progress Tracking
- Leave Management
- Feedback and Evaluation Submission
- Internship Completion Report Generation

### Admin
- User Account Management
- Role Assignment
- Coordinator Management
- Internship Posting
- System Monitoring
- Report Viewing

------------------------------------------------------------

## 4. Authentication and Security

- JWT-based Authentication
- Role-Based Access Control (RBAC)
- Encrypted Password Storage (BCrypt)
- Protected REST Endpoints
- Secure Session Handling
- Input Validation and Error Handling

------------------------------------------------------------

## 5. Technology Stack

Frontend:
- React.js
- Axios

Backend:
- Java (JDK 17)
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- REST APIs

Database:
- PostgreSQL
- JPA / Hibernate ORM
- Relational Data Modeling

Deployment:
- Render Cloud Platform
- CI/CD Integrated Deployment

------------------------------------------------------------

## 6. CI/CD and Deployment

The application is deployed on Render with continuous deployment enabled.

Any changes pushed to the main branch trigger:
- Automatic build process
- Backend compilation
- Deployment to production environment

Live URL:
https://internship-management-system-2c4j.onrender.com

------------------------------------------------------------

## 7. Installation and Setup

### Clone Repository

git clone https://github.com/srvstvshivam/Internship_Management_System.git
cd Internship_Management_System

### Backend Setup

cd backend
mvn clean install
mvn spring-boot:run

Backend runs on:
http://localhost:8082

Ensure PostgreSQL is installed and running.
Configure database credentials in application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/ims_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

### Frontend Setup

cd frontend
npm install
npm start

Frontend runs on:
http://localhost:3000

------------------------------------------------------------

## 8. Operating Environment

Client Side:
- Modern Web Browsers (Chrome, Firefox, Edge)
- Windows / Linux / macOS

Server Side:
- Spring Boot Application Server
- PostgreSQL Database

------------------------------------------------------------

## 9. Future Enhancements

- Email and Notification System
- Analytics Dashboard
- Multi-Centre Support
- Cloud Auto-Scaling
- Mobile Application
- ERP Integration

------------------------------------------------------------

## 10. Limitations

- No external ERP integration in current version
- No stipend or payroll processing
- Internet connectivity required
- No AI-based evaluation system

------------------------------------------------------------

Developed under:
Work-Based Learning (WBL) Program  
C-DAC Delhi  
Batch 2025–2026  

Under the guidance of:
Name-: Mr. Sunil Kumar  
Designation :
organizaton: C-DAC Delhi
program : E-Governance(WBL)
