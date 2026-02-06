package com.dinushka.internship_portal_api.repository;

import com.dinushka.internship_portal_api.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {}
