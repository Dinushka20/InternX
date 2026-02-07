package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.dto.CompanyApplicationListItemDto;
import com.dinushka.internship_portal_api.service.ApplicationService;
import com.dinushka.internship_portal_api.dto.UpdateApplicationStatusRequestDto;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyApplicationsController {

    private final ApplicationService applicationService;

    public CompanyApplicationsController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // GET /api/companies/{companyId}/applications
    @GetMapping("/{companyId}/applications")
    public List<CompanyApplicationListItemDto> listCompanyApplications(
            @PathVariable Long companyId
    ) {
        return applicationService.listCompanyApplications(companyId);
    }

    // PATCH /api/companies/{companyId}/applications/{applicationId}/status
    @PatchMapping("/{companyId}/applications/{applicationId}/status")
    public CompanyApplicationListItemDto updateApplicationStatus(
            @PathVariable Long companyId,
            @PathVariable Long applicationId,
            @Valid @RequestBody UpdateApplicationStatusRequestDto request
    ) {
        return applicationService.updateApplicationStatus(companyId, applicationId, request);
    }

}
