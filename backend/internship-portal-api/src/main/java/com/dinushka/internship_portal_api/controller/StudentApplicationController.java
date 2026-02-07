package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.dto.ApplicationListItemDto;
import com.dinushka.internship_portal_api.dto.ApplyJobRequestDto;
import com.dinushka.internship_portal_api.dto.ApplicationResponseDto;
import com.dinushka.internship_portal_api.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentApplicationController {

    private final ApplicationService applicationService;

    public StudentApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // POST /api/students/apply
    @PostMapping("/apply")
    public ApplicationResponseDto apply(@Valid @RequestBody ApplyJobRequestDto request) {
        return applicationService.apply(request);
    }

    // GET /api/students/{studentId}/applications
    @GetMapping("/{studentId}/applications")
    public List<ApplicationListItemDto> listStudentApplications(
            @PathVariable Long studentId) {
        return applicationService.listStudentApplications(studentId);
    }

}