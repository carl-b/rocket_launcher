package com.qfree.rocketlauncher.client.errorhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RestServiceException.class })
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        final RestServiceException restServiceException = (RestServiceException) e;

        final ErrorCode errorCode = restServiceException.getErrorCode();

        return handleExceptionInternal(
                restServiceException,
                getErrorResource(errorCode, restServiceException),
                getResponseHeaders(),
                errorCode.getHttpStatus(),
                request);
    }

    private ErrorResource getErrorResource(final ErrorCode errorCode, final Throwable throwable) {
        final String exceptionDetail = throwable == null ? null : throwable.getMessage();
        return new ErrorResource(
                String.valueOf(errorCode.getErrorCode()), errorCode.getErrorMessage(), exceptionDetail);
    }

    private HttpHeaders getResponseHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Allow-Origin", "*");
        return headers;
    }
}
