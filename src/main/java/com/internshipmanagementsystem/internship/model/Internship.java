package com.internshipmanagementsystem.internship.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "internships")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String domain;

    @Column(name = "tech_stack")
    private String techStack;

    @Column(name = "company_name", nullable = false)
    private String companyName = "C-DAC Delhi"; 

    private String sector;

    private String location;

    
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "duration_weeks")
    private Integer durationWeeks;

    @Column(name = "duration_in_months")
    private Integer durationInMonths;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    
    @Column(name = "max_students", nullable = false)
    private Integer maxStudents;

    @Column(name = "enrolled_students")
    private Integer enrolledStudents = 0;

    
    @Column(name = "eligibility_criteria", columnDefinition = "TEXT")
    private String eligibilityCriteria;

    @Column(name = "minimum_percentage")
    private Double minimumPercentage;

    @Column(name = "min_passout_year")
    private Integer minPassoutYear;

    @Column(name = "max_passout_year")
    private Integer maxPassoutYear;

    @Column(name = "no_active_backlogs_required")
    private Boolean noActiveBacklogsRequired = false;

    
    @Column(name = "stipend_amount")
    private Double stipendAmount;

    @Column(name = "is_paid")
    private Boolean isPaid = false;

    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InternshipStatus status = InternshipStatus.DRAFT;

    
    @Column(name = "schedule_approved")
    private Boolean scheduleApproved = false;

    @Column(name = "approved_by_mentor_id")
    private Long approvedByMentorId;

    
    @Column(name = "coordinator_id", nullable = false)
    private Long coordinatorId;

    @Column(name = "created_by_coordinator_id")
    private Long createdByCoordinatorId;

    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public String getTechStack() { return techStack; }
    public void setTechStack(String techStack) { this.techStack = techStack; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Integer getDurationWeeks() { return durationWeeks; }
    public void setDurationWeeks(Integer durationWeeks) { this.durationWeeks = durationWeeks; }

    public Integer getDurationInMonths() { return durationInMonths; }
    public void setDurationInMonths(Integer durationInMonths) { this.durationInMonths = durationInMonths; }

    public LocalDate getApplicationDeadline() { return applicationDeadline; }
    public void setApplicationDeadline(LocalDate applicationDeadline) { this.applicationDeadline = applicationDeadline; }

    public Integer getMaxStudents() { return maxStudents; }
    public void setMaxStudents(Integer maxStudents) { this.maxStudents = maxStudents; }

    public Integer getEnrolledStudents() { return enrolledStudents; }
    public void setEnrolledStudents(Integer enrolledStudents) { this.enrolledStudents = enrolledStudents; }

    public String getEligibilityCriteria() { return eligibilityCriteria; }
    public void setEligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; }

    public Double getMinimumPercentage() { return minimumPercentage; }
    public void setMinimumPercentage(Double minimumPercentage) { this.minimumPercentage = minimumPercentage; }

    public Integer getMinPassoutYear() { return minPassoutYear; }
    public void setMinPassoutYear(Integer minPassoutYear) { this.minPassoutYear = minPassoutYear; }

    public Integer getMaxPassoutYear() { return maxPassoutYear; }
    public void setMaxPassoutYear(Integer maxPassoutYear) { this.maxPassoutYear = maxPassoutYear; }

    public Boolean getNoActiveBacklogsRequired() { return noActiveBacklogsRequired; }
    public void setNoActiveBacklogsRequired(Boolean noActiveBacklogsRequired) { this.noActiveBacklogsRequired = noActiveBacklogsRequired; }

    public Double getStipendAmount() { return stipendAmount; }
    public void setStipendAmount(Double stipendAmount) { this.stipendAmount = stipendAmount; }

    public Boolean getIsPaid() { return isPaid; }
    public void setIsPaid(Boolean isPaid) { this.isPaid = isPaid; }

    public InternshipStatus getStatus() { return status; }
    public void setStatus(InternshipStatus status) { this.status = status; }

    public Boolean getScheduleApproved() { return scheduleApproved; }
    public void setScheduleApproved(Boolean scheduleApproved) { this.scheduleApproved = scheduleApproved; }

    public Long getApprovedByMentorId() { return approvedByMentorId; }
    public void setApprovedByMentorId(Long approvedByMentorId) { this.approvedByMentorId = approvedByMentorId; }

    public Long getCoordinatorId() { return coordinatorId; }
    public void setCoordinatorId(Long coordinatorId) { this.coordinatorId = coordinatorId; }

    public Long getCreatedByCoordinatorId() { return createdByCoordinatorId; }
    public void setCreatedByCoordinatorId(Long createdByCoordinatorId) { this.createdByCoordinatorId = createdByCoordinatorId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime publishedAt) { this.publishedAt = publishedAt; }
}