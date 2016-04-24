package com.qfree.rocketlauncher.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.qfree.rocketlauncher.client.errorhandling.ErrorCode;
import com.qfree.rocketlauncher.client.errorhandling.RestServiceException;
import com.qfree.rocketlauncher.model.JsonInputRocket;
import com.qfree.rocketlauncher.model.JsonRocket;

public class ControllerHelper {

    public static void validateCreateRocket(final JsonInputRocket rocket) {
        if (rocket == null) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_NO_ROCKET);
        }
        if (rocket.getName() == null || rocket.getName().isEmpty()) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_NO_NAME);
        }
    }

    public static void validateIntParameter(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_INVALID_INT_PARAMETER, "Could not create integer of: " + input);
        }
    }

    public static void validateRocketFound(final JsonRocket rocket, final String id) {
        if (rocket == null) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_ROCKET_NOT_FOUND, "Could not find rocket with id: " + id);
        }
    }


    public static HttpHeaders getResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Allow-Origin", "*");
        return headers;
    }
}
