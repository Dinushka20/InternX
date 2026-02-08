package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.dto.CompanyApplicationListItemDto;
import com.dinushka.internship_portal_api.dto.UpdateApplicationStatusRequestDto;
import com.dinushka.internship_portal_api.security.AuthUserUtil;
import com.dinushka.internship_portal_api.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies/me")
public class CompanyMeApplicationsController {

    private final ApplicationService applicationService;

    public CompanyMeApplicationsController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // GET /api/companies/me/applications
    @GetMapping("/applications")
    public List<CompanyApplicationListItemDto> myApplications() {
        Long companyId = AuthUserUtil.currentUserId();
        return applicationService.listMyCompanyApplications(companyId);
    }

    // PATCH /api/companies/me/applications/{applicationId}/status
    @PatchMapping("/applications/{applicationId}/status")
    public CompanyApplicationListItemDto updateStatus(
            @PathVariable Long applicationId,
            @Valid @RequestBody UpdateApplicationStatusRequestDto request
    ) {
        Long companyId = AuthUserUtil.currentUserId();
        return applicationService.updateMyCompanyApplicationStatus(companyId, applicationId, request);
    }
}
