package com.dinushka.internship_portal_api.dto;

import com.dinushka.internship_portal_api.enums.ApplicationStatus;

import java.time.Instant;

public class ApplicationResponseDto {

    private Long applicationId;
    private Long jobId;
    private Long studentId;
    private ApplicationStatus status;
    private Instant appliedAt;

    public ApplicationResponseDto() {}

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public Instant getAppliedAt() { return appliedAt; }
    public void setAppliedAt(Instant appliedAt) { this.appliedAt = appliedAt; }
}

