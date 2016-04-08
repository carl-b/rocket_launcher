package com.qfree.rocketlauncher.client;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfree.rocketlauncher.model.JsonRocket;
import com.qfree.rocketlauncher.model.Rocket;
import com.qfree.rocketlauncher.service.RocketLauncherServiceLayer;

@Controller
@EnableAutoConfiguration
public class RocketLauncherController {

    private RocketLauncherServiceLayer rocketLauncherServiceLayer;

    @Autowired
    public void setRocketLauncherServiceLayer(RocketLauncherServiceLayer rocketLauncherServiceLayer) {
        this.rocketLauncherServiceLayer = rocketLauncherServiceLayer;
    }

    @ResponseBody
    @RequestMapping(value = "/getRockets", method = RequestMethod.GET)
    public ResponseEntity<List<JsonRocket>> getRockets() {

        final List<JsonRocket> rockets = new ArrayList<>();

        rockets.add(generateRandomRocket());

        return new ResponseEntity<>(rockets, getResponseHeaders(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/getRocketsOnQueue", method = RequestMethod.GET)
    public ResponseEntity<List<JsonRocket>> getRockets(@RequestParam(defaultValue = "10") int limit) {

        final List<JsonRocket> rockets = rocketLauncherServiceLayer.getRocketsReadyForLaunch(limit);

        return new ResponseEntity<>(rockets, getResponseHeaders(), HttpStatus.OK);
    }



    private JsonRocket generateRandomRocket() {
        return new JsonRocket(
                -1,
                "Random",
                (int) (Math.random() * 100),
                (int) (Math.random() * -6 - 4),
                (int) (Math.random() * 6 - 3),
                String.valueOf(Math.floor(Math.random() * 360 / 10) * 10),
                (int) (Math.random() * 10 + 2));
    }

    private static HttpHeaders getResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Allow-Origin", "*");
        return headers;
    }

}
