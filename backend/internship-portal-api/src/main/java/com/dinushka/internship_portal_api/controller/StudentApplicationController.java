package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentApplicationController {

    // Legacy endpoints are disabled. Use /api/students/me/*
    @PostMapping("/apply")
    public void apply() {
        throw new NotFoundException("Endpoint removed. Use /api/students/me/apply");
    }

    @GetMapping("/{studentId}/applications")
    public void listStudentApplications(@PathVariable Long studentId) {
        throw new NotFoundException("Endpoint removed. Use /api/students/me/applications");
    }
}
