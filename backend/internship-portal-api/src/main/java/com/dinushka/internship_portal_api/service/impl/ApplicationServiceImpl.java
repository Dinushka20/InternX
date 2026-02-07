package com.dinushka.internship_portal_api.service.impl;

import com.dinushka.internship_portal_api.dto.ApplicationListItemDto;
import com.dinushka.internship_portal_api.dto.ApplyJobRequestDto;
import com.dinushka.internship_portal_api.dto.ApplicationResponseDto;
import com.dinushka.internship_portal_api.entity.Application;
import com.dinushka.internship_portal_api.entity.Job;
import com.dinushka.internship_portal_api.entity.StudentProfile;
import com.dinushka.internship_portal_api.enums.ApplicationStatus;
import com.dinushka.internship_portal_api.exception.BadRequestException;
import com.dinushka.internship_portal_api.exception.NotFoundException;
import com.dinushka.internship_portal_api.repository.ApplicationRepository;
import com.dinushka.internship_portal_api.repository.CompanyProfileRepository;
import com.dinushka.internship_portal_api.repository.JobRepository;
import com.dinushka.internship_portal_api.repository.StudentProfileRepository;
import com.dinushka.internship_portal_api.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final CompanyProfileRepository companyProfileRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  JobRepository jobRepository,
                                  StudentProfileRepository studentProfileRepository,
                                  CompanyProfileRepository companyProfileRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.companyProfileRepository = companyProfileRepository;
    }

    @Override
    public ApplicationResponseDto apply(ApplyJobRequestDto request) {
        Long jobId = request.getJobId();
        Long studentId = request.getStudentId();

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new NotFoundException("Job not found: " + jobId));

        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

        boolean alreadyApplied = applicationRepository.existsByJob_JobIdAndStudent_StudentId(jobId, studentId);
        if (alreadyApplied) {
            throw new BadRequestException("You have already applied for this job.");
        }

        Application application = new Application();
        application.setJob(job);
        application.setStudent(student);
        application.setStatus(ApplicationStatus.APPLIED);

        Application saved = applicationRepository.save(application);
        return toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationListItemDto> listStudentApplications(Long studentId) {
        // Validate student exists (nicer error than empty list)
        if (!studentProfileRepository.existsById(studentId)) {
            throw new NotFoundException("Student not found: " + studentId);
        }

        return applicationRepository.findByStudent_StudentIdOrderByAppliedAtDesc(studentId)
                .stream().map(this::toListItemDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationListItemDto> listCompanyApplications(Long companyId) {
        // Validate company exists
        if (!companyProfileRepository.existsById(companyId)) {
            throw new NotFoundException("Company not found: " + companyId);
        }

        return applicationRepository.findByJob_Company_CompanyIdOrderByAppliedAtDesc(companyId)
                .stream().map(this::toListItemDto).toList();
    }

    private ApplicationResponseDto toResponseDto(Application app) {
        ApplicationResponseDto dto = new ApplicationResponseDto();
        dto.setApplicationId(app.getApplicationId());
        dto.setJobId(app.getJob().getJobId());
        dto.setStudentId(app.getStudent().getStudentId());
        dto.setStatus(app.getStatus());
        dto.setAppliedAt(app.getAppliedAt());
        return dto;
    }

    private ApplicationListItemDto toListItemDto(Application app) {
        ApplicationListItemDto dto = new ApplicationListItemDto();
        dto.setApplicationId(app.getApplicationId());
        dto.setStatus(app.getStatus());
        dto.setAppliedAt(app.getAppliedAt());

        dto.setJobId(app.getJob().getJobId());
        dto.setJobTitle(app.getJob().getTitle());
        dto.setJobType(app.getJob().getJobType());

        dto.setCompanyId(app.getJob().getCompany().getCompanyId());
        dto.setCompanyName(app.getJob().getCompany().getCompanyName());
        return dto;
    }
}