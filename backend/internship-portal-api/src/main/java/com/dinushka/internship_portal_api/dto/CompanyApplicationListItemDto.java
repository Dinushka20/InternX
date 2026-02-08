package com.dinushka.internship_portal_api.dto;

import com.dinushka.internship_portal_api.enums.ApplicationStatus;
import com.dinushka.internship_portal_api.enums.JobType;

import java.time.Instant;

public class CompanyApplicationListItemDto {

    private Long applicationId;
    private Instant appliedAt;
    private ApplicationStatus status;

    private Long jobId;
    private String jobTitle;
    private JobType jobType;

    private Long studentId;
    private String studentName;

    private String university;
    private String degree;
    private Integer graduationYear;

    // ✅ do NOT expose file path
    private boolean cvUploaded;

    public CompanyApplicationListItemDto() {}

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }

    public Instant getAppliedAt() { return appliedAt; }
    public void setAppliedAt(Instant appliedAt) { this.appliedAt = appliedAt; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public JobType getJobType() { return jobType; }
    public void setJobType(JobType jobType) { this.jobType = jobType; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public Integer getGraduationYear() { return graduationYear; }
    public void setGraduationYear(Integer graduationYear) { this.graduationYear = graduationYear; }

    public boolean isCvUploaded() { return cvUploaded; }
    public void setCvUploaded(boolean cvUploaded) { this.cvUploaded = cvUploaded; }
}
