package com.dinushka.internship_portal_api.dto;

import com.dinushka.internship_portal_api.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateApplicationStatusRequestDto {

    @NotNull
    private ApplicationStatus status;

    public UpdateApplicationStatusRequestDto() {}

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
}
