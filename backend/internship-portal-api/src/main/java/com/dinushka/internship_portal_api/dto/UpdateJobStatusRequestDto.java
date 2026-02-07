package com.dinushka.internship_portal_api.dto;

import com.dinushka.internship_portal_api.enums.JobStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateJobStatusRequestDto {

    @NotNull
    private JobStatus status;

    public UpdateJobStatusRequestDto() {}

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }
}
