package com.dinushka.internship_portal_api.service;

import com.dinushka.internship_portal_api.dto.*;


import java.util.List;

public interface ApplicationService {

    ApplicationResponseDto apply(ApplyJobRequestDto request);

    List<ApplicationListItemDto> listStudentApplications(Long studentId);

    // Company view: includes applicant details
    List<CompanyApplicationListItemDto> listCompanyApplications(Long companyId);

    CompanyApplicationListItemDto updateApplicationStatus(Long companyId, Long applicationId, UpdateApplicationStatusRequestDto request);
}
