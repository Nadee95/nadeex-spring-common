package com.nadeex.spring.common.exception;

import lombok.Getter;

/**
 * Abstract base class for all custom exceptions in the nadeex ecosystem.
 * All domain-specific exceptions should extend this class.
 */
@Getter
public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    protected BaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    protected BaseException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}

