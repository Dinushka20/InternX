package com.dinushka.internship_portal_api.service.files;

import com.dinushka.internship_portal_api.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;

@Service
public class CvStorageServiceImpl implements CvStorageService {

    private final Path uploadRoot;

    public CvStorageServiceImpl(@Value("${app.upload.dir}") String uploadDir) {
        this.uploadRoot = Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    @Override
    public Path saveStudentCv(Long studentId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("CV file is required");
        }

        String original = StringUtils.cleanPath(file.getOriginalFilename() == null ? "cv" : file.getOriginalFilename());

        // basic extension allow-list
        String lower = original.toLowerCase();
        if (!(lower.endsWith(".pdf") || lower.endsWith(".doc") || lower.endsWith(".docx"))) {
            throw new BadRequestException("Only PDF/DOC/DOCX files are allowed");
        }

        // prevent path traversal
        if (original.contains("..")) {
            throw new BadRequestException("Invalid file name");
        }

        try {
            Path studentDir = uploadRoot.resolve("cv").resolve("student-" + studentId).normalize();
            Files.createDirectories(studentDir);

            String filename = "cv-" + Instant.now().toEpochMilli() + "-" + original;
            Path target = studentDir.resolve(filename).normalize();

            // ensure still under root
            if (!target.startsWith(uploadRoot)) {
                throw new BadRequestException("Invalid storage path");
            }

            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return target;
        } catch (IOException e) {
            throw new BadRequestException("Failed to store CV file");
        }
    }
}
