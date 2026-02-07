package com.dinushka.internship_portal_api.config;

import com.dinushka.internship_portal_api.security.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth
                // ✅ Public auth endpoints
                .requestMatchers("/api/auth/**").permitAll()

                // ✅ Public job browsing endpoints
                .requestMatchers(HttpMethod.GET, "/api/jobs/**").permitAll()

                // ✅ Student endpoints
                .requestMatchers("/api/students/**").hasRole("STUDENT")

                // ✅ Company endpoints
                .requestMatchers("/api/companies/**").hasRole("COMPANY")
                .requestMatchers("/api/company/**").hasRole("COMPANY")

                // everything else must be authenticated
                .anyRequest().authenticated()
        );

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
