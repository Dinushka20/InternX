package com.dinushka.internship_portal_api.dto;

import jakarta.validation.constraints.NotNull;

public class ApplyJobMeRequestDto {
    @NotNull
    private Long jobId;

    public ApplyJobMeRequestDto() {}

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
}
