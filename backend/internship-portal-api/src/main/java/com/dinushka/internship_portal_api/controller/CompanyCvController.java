package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.entity.Application;
import com.dinushka.internship_portal_api.entity.StudentProfile;
import com.dinushka.internship_portal_api.exception.BadRequestException;
import com.dinushka.internship_portal_api.exception.NotFoundException;
import com.dinushka.internship_portal_api.repository.ApplicationRepository;
import com.dinushka.internship_portal_api.security.AuthUserUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies/me")
public class CompanyCvController {

    private final ApplicationRepository applicationRepository;

    public CompanyCvController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("/applications/{applicationId}/cv/download")
    public ResponseEntity<Resource> downloadApplicantCv(@PathVariable Long applicationId) {

        Long companyId = AuthUserUtil.currentUserId();

        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application not found: " + applicationId));

        // ✅ ownership check
        Long appCompanyId = app.getJob().getCompany().getCompanyId();
        if (!appCompanyId.equals(companyId)) {
            throw new BadRequestException("You are not allowed to download this CV.");
        }

        StudentProfile student = app.getStudent();
        String cvPath = student.getCvUrl();
        if (cvPath == null || cvPath.isBlank()) {
            throw new NotFoundException("Student has not uploaded a CV.");
        }

        FileSystemResource resource = new FileSystemResource(cvPath);
        if (!resource.exists()) {
            throw new NotFoundException("CV file not found on server.");
        }

        String filename = resource.getFilename() == null ? "cv.pdf" : resource.getFilename();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
