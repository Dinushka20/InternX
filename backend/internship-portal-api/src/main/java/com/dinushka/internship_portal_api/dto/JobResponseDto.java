package com.dinushka.internship_portal_api.dto;

import com.dinushka.internship_portal_api.enums.JobStatus;
import com.dinushka.internship_portal_api.enums.JobType;

import java.time.Instant;

public class JobResponseDto {
    private Long jobId;
    private Long companyId;
    private String companyName;
    private String title;
    private String description;
    private JobType jobType;
    private String location;
    private Integer salary;
    private JobStatus status;
    private Instant createdAt;

    public JobResponseDto() {}

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public JobType getJobType() { return jobType; }
    public void setJobType(JobType jobType) { this.jobType = jobType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Integer getSalary() { return salary; }
    public void setSalary(Integer salary) { this.salary = salary; }

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
