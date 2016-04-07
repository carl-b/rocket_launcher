package com.qfree.rocketlauncher.client;


import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfree.rocketlauncher.model.Rocket;

@Controller
@EnableAutoConfiguration
public class RocketLauncherController {


    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "wello World!";
    }

    @ResponseBody
    @RequestMapping(value = "/getRockets", method = RequestMethod.GET)
    public ResponseEntity<List<Rocket>> getRockets() {

        final List<Rocket> rockets = new ArrayList<>();

        rockets.add(generateRandomRocket());

        return new ResponseEntity<>(rockets, getResponseHeaders(), HttpStatus.OK);
    }


    private Rocket generateRandomRocket() {
        return new Rocket.Builder()
                .setName("Random")
                .setColor(String.valueOf(Math.floor(Math.random() * 360 / 10) * 10))
                .setxPosition((int) (Math.random() * 100))
                .setyVelocity((int) (Math.random() * -6 - 4))
                .setxVelocity((int) (Math.random() * 6 - 3))
                .setSize((int) (Math.random() * 10 + 2))
                .build();
    }

    private static HttpHeaders getResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Allow-Origin", "*");
        return headers;
    }

}
