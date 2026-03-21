package com.nadeex.spring.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * Standardized error response returned by the global exception handler.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private int status;
    private String error;
    private String message;
    private String path;
    private Instant timestamp;
    private String errorCode;
    private List<ValidationError> validationErrors;

    public ErrorResponse() {}

    public ErrorResponse(int status, String error, String message, String path,
                         Instant timestamp, String errorCode, List<ValidationError> validationErrors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.validationErrors = validationErrors;
    }

    /**
     * Represents a single field-level validation error.
     */
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ValidationError {

        private String field;
        private String message;
        private Object rejectedValue;

        public ValidationError() {}

        public ValidationError(String field, String message, Object rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }
    }
}
