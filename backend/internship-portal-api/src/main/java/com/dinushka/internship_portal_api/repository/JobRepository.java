package com.dinushka.internship_portal_api.repository;

import com.dinushka.internship_portal_api.entity.Job;
import com.dinushka.internship_portal_api.enums.JobStatus;
import com.dinushka.internship_portal_api.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatusOrderByCreatedAtDesc(JobStatus status);
    List<Job> findByStatusAndJobTypeOrderByCreatedAtDesc(JobStatus status, JobType jobType);
    List<Job> findByCompany_CompanyIdOrderByCreatedAtDesc(Long companyId);
}
