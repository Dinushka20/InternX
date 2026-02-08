package com.dinushka.internship_portal_api.exception;

import java.time.Instant;
import java.util.Map;

public class ApiError {
    private Instant timestamp;
    private int status;
    private String error;
    private String path;
    private Map<String, String> fieldErrors;

    public ApiError() {}

    public ApiError(Instant timestamp, int status, String error, String path) {
        this(timestamp, status, error, path, null);
    }

    public ApiError(Instant timestamp, int status, String error, String path, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public Map<String, String> getFieldErrors() { return fieldErrors; }
    public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }
}
