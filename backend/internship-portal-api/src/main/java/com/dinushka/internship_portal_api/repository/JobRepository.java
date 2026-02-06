package com.dinushka.internship_portal_api.repository;

import com.dinushka.internship_portal_api.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatus(com.dinushka.internship_portal_api.enums.JobStatus status);
}
