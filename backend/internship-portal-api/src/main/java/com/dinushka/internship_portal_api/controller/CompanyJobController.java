package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company/jobs")
public class CompanyJobController {

    // Legacy endpoints are disabled. Use /api/companies/me/jobs
    @PostMapping
    public void createJob() {
        throw new NotFoundException("Endpoint removed. Use /api/companies/me/jobs");
    }

    @PatchMapping("/{id}/status")
    public void updateStatus(@PathVariable("id") Long id) {
        throw new NotFoundException("Endpoint removed. No public update endpoint");
    }
}
