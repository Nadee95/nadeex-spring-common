package com.nadeex.spring.common.exception;

/**
 * Enumeration of application-wide error codes used across all nadeex libraries.
 */
public enum ErrorCode {

    // General
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    BAD_REQUEST("BAD_REQUEST"),
    VALIDATION_ERROR("VALIDATION_ERROR"),

    // Resource
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
    RESOURCE_ALREADY_EXISTS("RESOURCE_ALREADY_EXISTS"),

    // Auth
    UNAUTHORIZED("UNAUTHORIZED"),
    FORBIDDEN("FORBIDDEN"),
    INVALID_TOKEN("INVALID_TOKEN"),
    TOKEN_EXPIRED("TOKEN_EXPIRED"),

    // Business
    BUSINESS_RULE_VIOLATION("BUSINESS_RULE_VIOLATION"),
    OPERATION_NOT_ALLOWED("OPERATION_NOT_ALLOWED");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

