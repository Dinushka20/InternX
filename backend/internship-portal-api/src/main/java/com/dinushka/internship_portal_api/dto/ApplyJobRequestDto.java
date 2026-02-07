package com.dinushka.internship_portal_api.dto;

import jakarta.validation.constraints.NotNull;

public class ApplyJobRequestDto {

    @NotNull
    private Long jobId;

    @NotNull
    private Long studentId;

    public ApplyJobRequestDto() {}

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
}
