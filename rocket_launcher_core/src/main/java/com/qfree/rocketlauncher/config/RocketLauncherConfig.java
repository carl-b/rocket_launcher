package com.qfree.rocketlauncher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qfree.rocketlauncher.persistence.RocketLauncherPersistenceLayer;
import com.qfree.rocketlauncher.persistence.memory.MemoryRocketLauncherPersistenceLayer;
import com.qfree.rocketlauncher.service.RocketLauncherServiceLayer;

@Configuration
public class RocketLauncherConfig {

    @Bean
    public RocketLauncherServiceLayer rocketLauncherServiceLayer() {
        return new RocketLauncherServiceLayer();
    }

    @Bean
    public RocketLauncherPersistenceLayer rocketLauncherPersistenceLayer() {
        return new MemoryRocketLauncherPersistenceLayer();
    }

}
