package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.dto.ApplyJobMeRequestDto;
import com.dinushka.internship_portal_api.dto.ApplicationListItemDto;
import com.dinushka.internship_portal_api.dto.ApplicationResponseDto;
import com.dinushka.internship_portal_api.security.AuthUserUtil;
import com.dinushka.internship_portal_api.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/me")
public class StudentMeController {

    private final ApplicationService applicationService;

    public StudentMeController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // GET /api/students/me/applications
    @GetMapping("/applications")
    public List<ApplicationListItemDto> myApplications() {
        Long userId = AuthUserUtil.currentUserId();
        return applicationService.listMyStudentApplications(userId);
    }

    // POST /api/students/me/apply
    @PostMapping("/apply")
    public ApplicationResponseDto apply(@Valid @RequestBody ApplyJobMeRequestDto request) {
        Long userId = AuthUserUtil.currentUserId();
        return applicationService.applyMe(userId, request);
    }
}
