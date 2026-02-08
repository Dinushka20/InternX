package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentCvController {

    // Legacy endpoints are disabled. Use /api/students/me/cv
    @PostMapping("/{studentId}/cv")
    public void uploadCv(@PathVariable Long studentId) {
        throw new NotFoundException("Endpoint removed. Use /api/students/me/cv");
    }

    @GetMapping("/{studentId}/cv/download")
    public void downloadCv(@PathVariable Long studentId) {
        throw new NotFoundException("Endpoint removed. Use /api/students/me/cv/download");
    }
}
