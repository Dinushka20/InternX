package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.dto.CreateJobRequestDto;
import com.dinushka.internship_portal_api.dto.JobResponseDto;
import com.dinushka.internship_portal_api.security.AuthUserUtil;
import com.dinushka.internship_portal_api.service.JobService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies/me/jobs")
public class CompanyMeJobsController {

    private final JobService jobService;

    public CompanyMeJobsController(JobService jobService) {
        this.jobService = jobService;
    }

    // POST /api/companies/me/jobs
    @PostMapping
    public JobResponseDto create(@Valid @RequestBody CreateJobRequestDto request) {
        Long companyId = AuthUserUtil.currentUserId();
        return jobService.createJobForCompany(companyId, request);
    }
}
