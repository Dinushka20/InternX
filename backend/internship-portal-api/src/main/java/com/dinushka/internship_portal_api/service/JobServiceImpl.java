package com.dinushka.internship_portal_api.service;

import com.dinushka.internship_portal_api.dto.CreateJobRequestDto;
import com.dinushka.internship_portal_api.dto.JobResponseDto;
import com.dinushka.internship_portal_api.dto.UpdateJobStatusRequestDto;
import com.dinushka.internship_portal_api.entity.CompanyProfile;
import com.dinushka.internship_portal_api.entity.Job;
import com.dinushka.internship_portal_api.enums.JobStatus;
import com.dinushka.internship_portal_api.enums.JobType;
import com.dinushka.internship_portal_api.exception.NotFoundException;
import com.dinushka.internship_portal_api.repository.CompanyProfileRepository;
import com.dinushka.internship_portal_api.repository.JobRepository;
import com.dinushka.internship_portal_api.service.JobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyProfileRepository companyProfileRepository;

    public JobServiceImpl(JobRepository jobRepository, CompanyProfileRepository companyProfileRepository) {
        this.jobRepository = jobRepository;
        this.companyProfileRepository = companyProfileRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobResponseDto> listOpenJobs(JobType jobType) {
        List<Job> jobs = (jobType == null)
                ? jobRepository.findByStatusOrderByCreatedAtDesc(JobStatus.OPEN)
                : jobRepository.findByStatusAndJobTypeOrderByCreatedAtDesc(JobStatus.OPEN, jobType);

        return jobs.stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public JobResponseDto getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new NotFoundException("Job not found: " + jobId));
        return toDto(job);
    }

    @Override
    public JobResponseDto createJob(CreateJobRequestDto request) {
        CompanyProfile company = companyProfileRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Company not found: " + request.getCompanyId()));

        Job job = new Job();
        job.setCompany(company);
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setJobType(request.getJobType());
        job.setStatus(JobStatus.OPEN);

        Job saved = jobRepository.save(job);
        return toDto(saved);
    }

    @Override
    public JobResponseDto updateJobStatus(Long jobId, UpdateJobStatusRequestDto request) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new NotFoundException("Job not found: " + jobId));

        job.setStatus(request.getStatus());
        Job saved = jobRepository.save(job);
        return toDto(saved);
    }

    private JobResponseDto toDto(Job job) {
        JobResponseDto dto = new JobResponseDto();
        dto.setJobId(job.getJobId());
        dto.setCompanyId(job.getCompany().getCompanyId());
        dto.setCompanyName(job.getCompany().getCompanyName());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setJobType(job.getJobType());
        dto.setStatus(job.getStatus());
        dto.setCreatedAt(job.getCreatedAt());
        return dto;
    }
}
