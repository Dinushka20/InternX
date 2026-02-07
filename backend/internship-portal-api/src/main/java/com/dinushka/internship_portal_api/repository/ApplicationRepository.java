package com.dinushka.internship_portal_api.repository;

import com.dinushka.internship_portal_api.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByJob_JobIdAndStudent_StudentId(Long jobId, Long studentId);

    List<Application> findByStudent_StudentIdOrderByAppliedAtDesc(Long studentId);

    List<Application> findByJob_Company_CompanyIdOrderByAppliedAtDesc(Long companyId);
}
