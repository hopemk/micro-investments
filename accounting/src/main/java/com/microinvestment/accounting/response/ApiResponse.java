package com.microinvestment.accounting.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON output
public class ApiResponse<T> {

    private String status; // e.g., "success", "error"
    private String message;
    private T data; // The actual payload
    private Object metadata; // For pagination, additional info, etc.
    private Object errors; // For validation errors, detailed error messages

    public ApiResponse(String status, String message, T data, Object metadata, Object errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.metadata = metadata;
        this.errors = errors;
    }

    // Getters and Setters (omitted for brevity)

    // --- Static helper methods for convenience ---

    public static <T> ApiResponse<T> success(String message, T data, Object metadata) {
        return new ApiResponse<>("success", message, data, metadata, null);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return success(message, data, null);
    }

    public static <T> ApiResponse<T> error(String message, Object errors) {
        return new ApiResponse<>("error", message, null, null, errors);
    }

    public static <T> ApiResponse<T> error(String message) {
        return error(message, null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
