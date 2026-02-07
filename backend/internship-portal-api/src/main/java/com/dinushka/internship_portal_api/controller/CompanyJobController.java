package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.dto.CreateJobRequestDto;
import com.dinushka.internship_portal_api.dto.JobResponseDto;
import com.dinushka.internship_portal_api.dto.UpdateJobStatusRequestDto;
import com.dinushka.internship_portal_api.service.JobService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company/jobs")
public class CompanyJobController {

    private final JobService jobService;

    public CompanyJobController(JobService jobService) {
        this.jobService = jobService;
    }

    // POST /api/company/jobs
    @PostMapping
    public JobResponseDto createJob(@Valid @RequestBody CreateJobRequestDto request) {
        return jobService.createJob(request);
    }

    // PATCH /api/company/jobs/{id}/status
    @PatchMapping("/{id}/status")
    public JobResponseDto updateStatus(@PathVariable("id") Long id,
                                       @Valid @RequestBody UpdateJobStatusRequestDto request) {
        return jobService.updateJobStatus(id, request);
    }
}
