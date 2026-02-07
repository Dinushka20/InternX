package com.dinushka.internship_portal_api.service;

import com.dinushka.internship_portal_api.dto.ApplicationListItemDto;
import com.dinushka.internship_portal_api.dto.ApplyJobRequestDto;
import com.dinushka.internship_portal_api.dto.ApplicationResponseDto;
import com.dinushka.internship_portal_api.dto.CompanyApplicationListItemDto;

import java.util.List;

public interface ApplicationService {

    ApplicationResponseDto apply(ApplyJobRequestDto request);

    List<ApplicationListItemDto> listStudentApplications(Long studentId);

    // Company view: includes applicant details
    List<CompanyApplicationListItemDto> listCompanyApplications(Long companyId);
}
