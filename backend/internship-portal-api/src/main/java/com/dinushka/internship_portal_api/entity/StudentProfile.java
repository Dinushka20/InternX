package com.dinushka.internship_portal_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "student_id")
    private User user;

    @Column(name = "university", length = 150)
    private String university;

    @Column(name = "degree", length = 150)
    private String degree;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    @Column(name = "cv_url", length = 255)
    private String cvUrl;

    public StudentProfile() {}

    // Getters & Setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public Integer getGraduationYear() { return graduationYear; }
    public void setGraduationYear(Integer graduationYear) { this.graduationYear = graduationYear; }

    public String getCvUrl() { return cvUrl; }
    public void setCvUrl(String cvUrl) { this.cvUrl = cvUrl; }
}
