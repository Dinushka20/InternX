package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.dto.ApplicationListItemDto;
import com.dinushka.internship_portal_api.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

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
    public List<ApplicationListItemDto> listCompanyApplications(@PathVariable Long companyId) {
        return applicationService.listCompanyApplications(companyId);
    }
}
