package com.internshipmanagementsystem.internship.dto;

import java.time.LocalDate;

public class InternshipRequest {

    private String title;
    private String description;
    private String domain;
    private String techStack;
    private String sector;
    private String location;

   
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer durationWeeks;
    private Integer durationInMonths;
    private LocalDate applicationDeadline;

   
    private Integer maxStudents;

    
    private String eligibilityCriteria;
    private Double minimumPercentage;
    private Integer minPassoutYear;
    private Integer maxPassoutYear;
    private Boolean noActiveBacklogsRequired;

   
    private Double stipendAmount;
    private Boolean isPaid;

   

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public String getTechStack() { return techStack; }
    public void setTechStack(String techStack) { this.techStack = techStack; }

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
}