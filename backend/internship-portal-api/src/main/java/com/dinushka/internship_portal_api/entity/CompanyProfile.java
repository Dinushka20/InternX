package com.dinushka.internship_portal_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "company_profiles")
public class CompanyProfile {

    @Id
    @Column(name = "company_id")
    private Long companyId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "company_id")
    private User user;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "website", length = 150)
    private String website;

    @Column(name = "location", length = 150)
    private String location;

    public CompanyProfile() {}

    // Getters & Setters
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
