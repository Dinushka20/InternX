package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyApplicationsController {

    // Legacy endpoints are disabled. Use /api/companies/me/applications
    @GetMapping("/{companyId}/applications")
    public void listCompanyApplications(@PathVariable Long companyId) {
        throw new NotFoundException("Endpoint removed. Use /api/companies/me/applications");
    }

    @PatchMapping("/{companyId}/applications/{applicationId}/status")
    public void updateApplicationStatus(@PathVariable Long companyId,
                                        @PathVariable Long applicationId) {
        throw new NotFoundException("Endpoint removed. Use /api/companies/me/applications/{applicationId}/status");
    }
}
