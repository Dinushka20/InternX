package com.dinushka.internship_portal_api.service.impl;

import com.dinushka.internship_portal_api.dto.*;
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
        if (!studentProfileRepository.existsById(studentId)) {
            throw new NotFoundException("Student not found: " + studentId);
        }

        return applicationRepository.findByStudent_StudentIdOrderByAppliedAtDesc(studentId)
                .stream()
                .map(this::toListItemDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyApplicationListItemDto> listCompanyApplications(Long companyId) {
        if (!companyProfileRepository.existsById(companyId)) {
            throw new NotFoundException("Company not found: " + companyId);
        }

        return applicationRepository.findByJob_Company_CompanyIdOrderByAppliedAtDesc(companyId)
                .stream()
                .map(this::toCompanyListItemDto)
                .toList();
    }

    @Override
    public CompanyApplicationListItemDto updateApplicationStatus(
            Long companyId,
            Long applicationId,
            UpdateApplicationStatusRequestDto request
    ) {
        // Validate company exists
        if (!companyProfileRepository.existsById(companyId)) {
            throw new NotFoundException("Company not found: " + companyId);
        }

        // Ensure the application belongs to this company (prevents changing other company's applications)
        boolean allowed = applicationRepository.existsByApplicationIdAndJob_Company_CompanyId(applicationId, companyId);
        if (!allowed) {
            // 404 is safer than 403 in a simple app (doesn't leak data)
            throw new NotFoundException("Application not found for this company: " + applicationId);
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application not found: " + applicationId));

        application.setStatus(request.getStatus());

        Application saved = applicationRepository.save(application);
        return toCompanyListItemDto(saved);
    }

    @Override
    public ApplicationResponseDto applyMe(Long studentUserId, ApplyJobMeRequestDto request) {
        // studentUserId is same as student_id because of MapsId
        ApplyJobRequestDto dto = new ApplyJobRequestDto();
        dto.setJobId(request.getJobId());
        dto.setStudentId(studentUserId);
        return apply(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationListItemDto> listMyStudentApplications(Long studentUserId) {
        return listStudentApplications(studentUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyApplicationListItemDto> listMyCompanyApplications(Long companyUserId) {
        return listCompanyApplications(companyUserId);
    }

    @Override
    public CompanyApplicationListItemDto updateMyCompanyApplicationStatus(Long companyUserId, Long applicationId, UpdateApplicationStatusRequestDto request) {
        return updateApplicationStatus(companyUserId, applicationId, request);
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

    // Student view list item (same as before)
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

    // Company view list item (includes applicant details)
    private CompanyApplicationListItemDto toCompanyListItemDto(Application app) {
        CompanyApplicationListItemDto dto = new CompanyApplicationListItemDto();

        dto.setApplicationId(app.getApplicationId());
        dto.setStatus(app.getStatus());
        dto.setAppliedAt(app.getAppliedAt());

        dto.setJobId(app.getJob().getJobId());
        dto.setJobTitle(app.getJob().getTitle());
        dto.setJobType(app.getJob().getJobType());

        dto.setStudentId(app.getStudent().getStudentId());
        dto.setStudentName(app.getStudent().getUser().getFullName());
        dto.setUniversity(app.getStudent().getUniversity());
        dto.setDegree(app.getStudent().getDegree());
        dto.setGraduationYear(app.getStudent().getGraduationYear());
        dto.setCvUrl(app.getStudent().getCvUrl());

        return dto;
    }
}
