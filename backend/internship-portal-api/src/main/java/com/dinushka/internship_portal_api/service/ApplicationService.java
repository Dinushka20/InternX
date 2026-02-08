package com.dinushka.internship_portal_api.service;

import com.dinushka.internship_portal_api.dto.*;
import com.dinushka.internship_portal_api.dto.ApplyJobMeRequestDto;


import java.util.List;

public interface ApplicationService {

    ApplicationResponseDto apply(ApplyJobRequestDto request);

    List<ApplicationListItemDto> listStudentApplications(Long studentId);

    // Company view: includes applicant details
    List<CompanyApplicationListItemDto> listCompanyApplications(Long companyId);

    CompanyApplicationListItemDto updateApplicationStatus(Long companyId, Long applicationId, UpdateApplicationStatusRequestDto request);

    ApplicationResponseDto applyMe(Long studentUserId, ApplyJobMeRequestDto request);

    List<ApplicationListItemDto> listMyStudentApplications(Long studentUserId);

    List<CompanyApplicationListItemDto> listMyCompanyApplications(Long companyUserId);

    CompanyApplicationListItemDto updateMyCompanyApplicationStatus(Long companyUserId, Long applicationId, UpdateApplicationStatusRequestDto request);

}
