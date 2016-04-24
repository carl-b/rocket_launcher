package com.qfree.rocketlauncher.client.errorhandling;

public class RestServiceException extends RuntimeException {

    private final ErrorCode errorCode;

    public RestServiceException(final ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public RestServiceException(final ErrorCode errorCode, final Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
    }

    public RestServiceException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
