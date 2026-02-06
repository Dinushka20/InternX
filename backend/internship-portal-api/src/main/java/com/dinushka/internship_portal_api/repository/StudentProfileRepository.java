package com.dinushka.internship_portal_api.repository;

import com.dinushka.internship_portal_api.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {}
