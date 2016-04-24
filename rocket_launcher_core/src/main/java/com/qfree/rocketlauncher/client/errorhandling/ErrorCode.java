package com.qfree.rocketlauncher.client.errorhandling;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    INVALID_REQUEST_NO_ROCKET(100, "No rocket in request", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_NO_NAME(101, "No 'name' set in request", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_INVALID_INT_PARAMETER(102, "Could not parse integer parameter", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_ROCKET_NOT_FOUND(103, "The rocket was not found", HttpStatus.NOT_FOUND),

    UNKNOWN_ERROR(900, "Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    private ErrorCode(final int errorCode, final String errorMessage, final HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
