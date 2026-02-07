package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.dto.JobResponseDto;
import com.dinushka.internship_portal_api.enums.JobType;
import com.dinushka.internship_portal_api.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // GET /api/jobs?jobType=INTERNSHIP
    @GetMapping
    public List<JobResponseDto> listOpenJobs(@RequestParam(required = false) JobType jobType) {
        return jobService.listOpenJobs(jobType);
    }

    // GET /api/jobs/1
    @GetMapping("/{id}")
    public JobResponseDto getJob(@PathVariable("id") Long id) {
        return jobService.getJobById(id);
    }
}

