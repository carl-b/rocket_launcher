package com.qfree.rocketlauncher.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.qfree.rocketlauncher.client.errorhandling.ErrorCode;
import com.qfree.rocketlauncher.client.errorhandling.RestServiceException;
import com.qfree.rocketlauncher.model.JsonInputRocket;
import com.qfree.rocketlauncher.model.JsonRocket;

public class ControllerHelper {

    public static void validateInputRocket(final JsonInputRocket rocket) {
        if (rocket == null) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_NO_ROCKET);
        }
        if (rocket.getName() == null || rocket.getName().isEmpty()) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_NO_NAME);
        }

        if (rocket.getSize() < 0 || rocket.getSize() > 1000) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_PARAMETER_VALUE, "Invalid value for parameter 'size'. Min: 0, Max: 1000");
        }

        if (rocket.getxPosition() < 0 || rocket.getxPosition() > 100) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_PARAMETER_VALUE, "Invalid value for parameter 'xPosition'. Min: 0, Max: 100");
        }

        if (rocket.getyVelocity() < -20 || rocket.getyVelocity() > 0) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_PARAMETER_VALUE, "Invalid value for parameter 'yVelocity'. Min: -20, Max: 0");
        }

        if (rocket.getxVelocity() < -50 || rocket.getxVelocity() > 50) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_PARAMETER_VALUE, "Invalid value for parameter 'xVelocity'. Min: -50, Max: 50");
        }

        if (rocket.getColor() < 0 || rocket.getColor() > 360) {
            throw new RestServiceException(ErrorCode.INVALID_REQUEST_PARAMETER_VALUE, "Invalid value for parameter 'color'. Min: 0, Max: 360");
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
