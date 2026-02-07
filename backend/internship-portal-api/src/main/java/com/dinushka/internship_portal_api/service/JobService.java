package com.dinushka.internship_portal_api.service;

import com.dinushka.internship_portal_api.dto.CreateJobRequestDto;
import com.dinushka.internship_portal_api.dto.JobResponseDto;
import com.dinushka.internship_portal_api.dto.UpdateJobStatusRequestDto;
import com.dinushka.internship_portal_api.enums.JobType;

import java.util.List;

public interface JobService {
    List<JobResponseDto> listOpenJobs(JobType jobType);
    JobResponseDto getJobById(Long jobId);
    JobResponseDto createJob(CreateJobRequestDto request);
    JobResponseDto updateJobStatus(Long jobId, UpdateJobStatusRequestDto request);
}