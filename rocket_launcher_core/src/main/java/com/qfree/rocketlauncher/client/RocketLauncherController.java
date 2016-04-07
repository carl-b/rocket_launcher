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

@Controller
@EnableAutoConfiguration
public class RocketLauncherController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "wello World!";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAvailableLists() {
        final List<String> actionLists = new ArrayList<>();
        actionLists.add("test1");
        return new ResponseEntity<>(actionLists, getResponseHeaders(), HttpStatus.OK);
    }

    private static HttpHeaders getResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Access-Control-Allow-Origin", "*");
        return headers;
    }

}
