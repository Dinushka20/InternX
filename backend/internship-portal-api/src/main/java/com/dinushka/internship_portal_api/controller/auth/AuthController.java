package com.dinushka.internship_portal_api.controller.auth;

import com.dinushka.internship_portal_api.dto.auth.*;
import com.dinushka.internship_portal_api.security.AuthUserUtil;
import com.dinushka.internship_portal_api.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/student")
    public AuthResponse registerStudent(@Valid @RequestBody RegisterStudentRequest req) {
        return authService.registerStudent(req);
    }

    @PostMapping("/register/company")
    public AuthResponse registerCompany(@Valid @RequestBody RegisterCompanyRequest req) {
        return authService.registerCompany(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me() {
        Long userId = AuthUserUtil.currentUserId();
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(new MeResponse(
                userId,
                AuthUserUtil.currentEmail(),
                AuthUserUtil.currentRole()
        ));
    }
}
