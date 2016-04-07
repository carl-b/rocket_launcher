package com.qfree.rocketlauncher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.qfree.rocketlauncher.client.RocketLauncherController;

@EnableAutoConfiguration
public class StartRocketLauncher {

    public static void main(String args[]) {

        SpringApplication.run(RocketLauncherController.class, args);
    }
}
