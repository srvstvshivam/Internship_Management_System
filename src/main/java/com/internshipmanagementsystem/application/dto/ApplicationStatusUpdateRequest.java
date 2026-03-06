package com.internshipmanagementsystem.application.dto;

public class ApplicationStatusUpdateRequest {

    private String status;              
    private String coordinatorRemarks;  
    private Long coordinatorId;         

   
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCoordinatorRemarks() { return coordinatorRemarks; }
    public void setCoordinatorRemarks(String coordinatorRemarks) { this.coordinatorRemarks = coordinatorRemarks; }

    public Long getCoordinatorId() { return coordinatorId; }
    public void setCoordinatorId(Long coordinatorId) { this.coordinatorId = coordinatorId; }
}