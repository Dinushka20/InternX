package com.dinushka.internship_portal_api.service;

import com.dinushka.internship_portal_api.dto.ApplyJobRequestDto;
import com.dinushka.internship_portal_api.dto.ApplicationResponseDto;

public interface ApplicationService {
    ApplicationResponseDto apply(ApplyJobRequestDto request);
}