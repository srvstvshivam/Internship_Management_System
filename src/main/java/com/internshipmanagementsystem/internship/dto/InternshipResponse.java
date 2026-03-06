package com.internshipmanagementsystem.internship.dto;

import com.internshipmanagementsystem.internship.model.InternshipStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InternshipResponse {

    private Long id;
    private String title;
    private String description;
    private String domain;
    private String techStack;
    private String companyName;
    private String sector;
    private String location;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer durationWeeks;
    private Integer durationInMonths;
    private LocalDate applicationDeadline;

    private Integer maxStudents;
    private Integer enrolledStudents;
    private Integer availableSlots;

    private String eligibilityCriteria;
    private Double minimumPercentage;
    private Integer minPassoutYear;
    private Integer maxPassoutYear;
    private Boolean noActiveBacklogsRequired;

    private Double stipendAmount;
    private Boolean isPaid;

    private InternshipStatus status;
    private Boolean scheduleApproved;
    private Long coordinatorId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

   
    public InternshipResponse(Long id, String title, String description, String domain,
                               String techStack, String companyName, String sector, String location,
                               LocalDate startDate, LocalDate endDate, Integer durationWeeks,
                               Integer durationInMonths, LocalDate applicationDeadline,
                               Integer maxStudents, Integer enrolledStudents,
                               String eligibilityCriteria, Double minimumPercentage,
                               Integer minPassoutYear, Integer maxPassoutYear,
                               Boolean noActiveBacklogsRequired, Double stipendAmount,
                               Boolean isPaid, InternshipStatus status, Boolean scheduleApproved,
                               Long coordinatorId, LocalDateTime createdAt,
                               LocalDateTime updatedAt, LocalDateTime publishedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.domain = domain;
        this.techStack = techStack;
        this.companyName = companyName;
        this.sector = sector;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationWeeks = durationWeeks;
        this.durationInMonths = durationInMonths;
        this.applicationDeadline = applicationDeadline;
        this.maxStudents = maxStudents;
        this.enrolledStudents = enrolledStudents;
        this.availableSlots = (maxStudents != null && enrolledStudents != null)
                ? maxStudents - enrolledStudents : 0;
        this.eligibilityCriteria = eligibilityCriteria;
        this.minimumPercentage = minimumPercentage;
        this.minPassoutYear = minPassoutYear;
        this.maxPassoutYear = maxPassoutYear;
        this.noActiveBacklogsRequired = noActiveBacklogsRequired;
        this.stipendAmount = stipendAmount;
        this.isPaid = isPaid;
        this.status = status;
        this.scheduleApproved = scheduleApproved;
        this.coordinatorId = coordinatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
    }

    
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDomain() { return domain; }
    public String getTechStack() { return techStack; }
    public String getCompanyName() { return companyName; }
    public String getSector() { return sector; }
    public String getLocation() { return location; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public Integer getDurationWeeks() { return durationWeeks; }
    public Integer getDurationInMonths() { return durationInMonths; }
    public LocalDate getApplicationDeadline() { return applicationDeadline; }
    public Integer getMaxStudents() { return maxStudents; }
    public Integer getEnrolledStudents() { return enrolledStudents; }
    public Integer getAvailableSlots() { return availableSlots; }
    public String getEligibilityCriteria() { return eligibilityCriteria; }
    public Double getMinimumPercentage() { return minimumPercentage; }
    public Integer getMinPassoutYear() { return minPassoutYear; }
    public Integer getMaxPassoutYear() { return maxPassoutYear; }
    public Boolean getNoActiveBacklogsRequired() { return noActiveBacklogsRequired; }
    public Double getStipendAmount() { return stipendAmount; }
    public Boolean getIsPaid() { return isPaid; }
    public InternshipStatus getStatus() { return status; }
    public Boolean getScheduleApproved() { return scheduleApproved; }
    public Long getCoordinatorId() { return coordinatorId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
}