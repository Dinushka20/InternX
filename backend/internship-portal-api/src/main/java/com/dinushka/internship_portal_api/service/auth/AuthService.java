package com.dinushka.internship_portal_api.service.auth;

import com.dinushka.internship_portal_api.dto.auth.*;

public interface AuthService {
    AuthResponse registerStudent(RegisterStudentRequest req);
    AuthResponse registerCompany(RegisterCompanyRequest req);
    AuthResponse login(LoginRequest req);
}
