package com.dinushka.internship_portal_api.controller;

import com.dinushka.internship_portal_api.entity.StudentProfile;
import com.dinushka.internship_portal_api.exception.NotFoundException;
import com.dinushka.internship_portal_api.repository.StudentProfileRepository;
import com.dinushka.internship_portal_api.service.files.CvStorageService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequestMapping("/api/students")
public class StudentCvController {

    private final StudentProfileRepository studentRepo;
    private final CvStorageService cvStorageService;

    public StudentCvController(StudentProfileRepository studentRepo, CvStorageService cvStorageService) {
        this.studentRepo = studentRepo;
        this.cvStorageService = cvStorageService;
    }

    // POST /api/students/{studentId}/cv (multipart form-data: file)
    @PostMapping("/{studentId}/cv")
    public ResponseEntity<?> uploadCv(@PathVariable Long studentId,
                                      @RequestParam("file") MultipartFile file) {

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

        Path savedPath = cvStorageService.saveStudentCv(studentId, file);

        // store relative path for portability
        student.setCvUrl(savedPath.toString());
        studentRepo.save(student);

        return ResponseEntity.ok().body(java.util.Map.of(
                "message", "CV uploaded successfully",
                "cvUrl", student.getCvUrl()
        ));
    }

    // GET /api/students/{studentId}/cv/download
    @GetMapping("/{studentId}/cv/download")
    public ResponseEntity<Resource> downloadCv(@PathVariable Long studentId) {

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

        String cvPath = student.getCvUrl();
        if (cvPath == null || cvPath.isBlank()) {
            throw new NotFoundException("CV not uploaded for student: " + studentId);
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
