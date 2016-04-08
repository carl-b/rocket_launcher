package com.qfree.rocketlauncher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.qfree.rocketlauncher.client.RocketLauncherController;
import com.qfree.rocketlauncher.client.RocketManagementController;
import com.qfree.rocketlauncher.config.RocketLauncherConfig;

@EnableAutoConfiguration
public class StartRocketLauncher {

    private final static Class[] configurationClasses
            = { RocketLauncherController.class, RocketManagementController.class,  RocketLauncherConfig.class };

    public static void main(String args[]) {

        SpringApplication.run(configurationClasses, args);
    }
}
