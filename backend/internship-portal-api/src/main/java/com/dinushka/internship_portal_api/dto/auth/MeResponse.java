package com.dinushka.internship_portal_api.dto.auth;

public class MeResponse {
    private Long userId;
    private String email;
    private String role;

    public MeResponse() {}

    public MeResponse(Long userId, String email, String role) {
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
