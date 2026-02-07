package com.dinushka.internship_portal_api.dto;

import com.dinushka.internship_portal_api.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateJobRequestDto {

    @NotNull
    private Long companyId;

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private JobType jobType;

    public CreateJobRequestDto() {}

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public JobType getJobType() { return jobType; }
    public void setJobType(JobType jobType) { this.jobType = jobType; }
}
