package com.dinushka.internship_portal_api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class AuthUserUtil {

    private AuthUserUtil() {}

    public static CustomUserDetails currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null || !(auth.getPrincipal() instanceof CustomUserDetails)) {
            return null;
        }
        return (CustomUserDetails) auth.getPrincipal();
    }

    public static Long currentUserId() {
        CustomUserDetails u = currentUser();
        return u == null ? null : u.getUserId();
    }

    public static String currentRole() {
        CustomUserDetails u = currentUser();
        return u == null ? null : u.getRoleName(); // "STUDENT" / "COMPANY"
    }

    public static String currentEmail() {
        CustomUserDetails u = currentUser();
        return u == null ? null : u.getUsername();
    }
}
