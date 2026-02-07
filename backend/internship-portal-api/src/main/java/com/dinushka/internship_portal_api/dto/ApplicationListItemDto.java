package com.dinushka.internship_portal_api.dto;

import com.dinushka.internship_portal_api.enums.ApplicationStatus;
import com.dinushka.internship_portal_api.enums.JobType;

import java.time.Instant;

public class ApplicationListItemDto {

    private Long applicationId;
    private ApplicationStatus status;
    private Instant appliedAt;

    private Long jobId;
    private String jobTitle;
    private JobType jobType;

    private Long companyId;
    private String companyName;

    public ApplicationListItemDto() {}

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public Instant getAppliedAt() { return appliedAt; }
    public void setAppliedAt(Instant appliedAt) { this.appliedAt = appliedAt; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public JobType getJobType() { return jobType; }
    public void setJobType(JobType jobType) { this.jobType = jobType; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}
