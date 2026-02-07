package com.dinushka.internship_portal_api.service.impl;

import com.dinushka.internship_portal_api.dto.ApplyJobRequestDto;
import com.dinushka.internship_portal_api.dto.ApplicationResponseDto;
import com.dinushka.internship_portal_api.entity.Application;
import com.dinushka.internship_portal_api.entity.Job;
import com.dinushka.internship_portal_api.entity.StudentProfile;
import com.dinushka.internship_portal_api.enums.ApplicationStatus;
import com.dinushka.internship_portal_api.exception.BadRequestException;
import com.dinushka.internship_portal_api.exception.NotFoundException;
import com.dinushka.internship_portal_api.repository.ApplicationRepository;
import com.dinushka.internship_portal_api.repository.JobRepository;
import com.dinushka.internship_portal_api.repository.StudentProfileRepository;
import com.dinushka.internship_portal_api.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final StudentProfileRepository studentProfileRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  JobRepository jobRepository,
                                  StudentProfileRepository studentProfileRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public ApplicationResponseDto apply(ApplyJobRequestDto request) {
        Long jobId = request.getJobId();
        Long studentId = request.getStudentId();

        // 1) Ensure job exists
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new NotFoundException("Job not found: " + jobId));

        // 2) Ensure student exists
        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

        // 3) Prevent duplicates BEFORE hitting UNIQUE constraint
        boolean alreadyApplied = applicationRepository.existsByJob_JobIdAndStudent_StudentId(jobId, studentId);
        if (alreadyApplied) {
            throw new BadRequestException("You have already applied for this job.");
        }

        // 4) Create application
        Application application = new Application();
        application.setJob(job);
        application.setStudent(student);
        application.setStatus(ApplicationStatus.APPLIED);

        Application saved = applicationRepository.save(application);
        return toDto(saved);
    }

    private ApplicationResponseDto toDto(Application app) {
        ApplicationResponseDto dto = new ApplicationResponseDto();
        dto.setApplicationId(app.getApplicationId());
        dto.setJobId(app.getJob().getJobId());
        dto.setStudentId(app.getStudent().getStudentId());
        dto.setStatus(app.getStatus());
        dto.setAppliedAt(app.getAppliedAt());
        return dto;
    }
}
