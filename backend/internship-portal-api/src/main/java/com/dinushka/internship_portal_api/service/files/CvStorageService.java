package com.dinushka.internship_portal_api.service.files;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface CvStorageService {
    Path saveStudentCv(Long studentId, MultipartFile file);
}
