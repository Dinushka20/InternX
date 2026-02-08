package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.entity.StudentProfile;
import com.dinushka.internship_portal_api.exception.NotFoundException;
import com.dinushka.internship_portal_api.repository.StudentProfileRepository;
import com.dinushka.internship_portal_api.security.AuthUserUtil;
import com.dinushka.internship_portal_api.service.files.CvStorageService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Map;

@RestController
@RequestMapping("/api/students/me/cv")
public class StudentMeCvController {

    private final StudentProfileRepository studentRepo;
    private final CvStorageService cvStorageService;

    public StudentMeCvController(StudentProfileRepository studentRepo, CvStorageService cvStorageService) {
        this.studentRepo = studentRepo;
        this.cvStorageService = cvStorageService;
    }

    // POST /api/students/me/cv  (multipart: file)
    @PostMapping
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        Long studentId = AuthUserUtil.currentUserId();

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

        Path saved = cvStorageService.saveStudentCv(studentId, file);
        student.setCvUrl(saved.toString());
        studentRepo.save(student);

        return ResponseEntity.ok(Map.of(
                "message", "CV uploaded successfully",
                "cvUploaded", true
        ));
    }

    // GET /api/students/me/cv/download
    @GetMapping("/download")
    public ResponseEntity<Resource> download() {
        Long studentId = AuthUserUtil.currentUserId();

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

        String cvPath = student.getCvUrl();
        if (cvPath == null || cvPath.isBlank()) {
            throw new NotFoundException("CV not uploaded");
        }

        FileSystemResource resource = new FileSystemResource(cvPath);
        if (!resource.exists()) {
            throw new NotFoundException("CV file not found on server");
        }

        String filename = resource.getFilename() == null ? "cv" : resource.getFilename();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
