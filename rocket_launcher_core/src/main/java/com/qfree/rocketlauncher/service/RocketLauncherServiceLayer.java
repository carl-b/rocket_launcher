package com.qfree.rocketlauncher.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qfree.rocketlauncher.model.JsonInputRocket;
import com.qfree.rocketlauncher.model.JsonRocket;
import com.qfree.rocketlauncher.model.Rocket;
import com.qfree.rocketlauncher.persistence.RocketLauncherPersistenceLayer;

public class RocketLauncherServiceLayer {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private RocketLauncherPersistenceLayer rocketLauncherPersistenceLayer;

    private Queue<JsonRocket> rocketsReadyForLaunch = new LinkedTransferQueue<>();

    @Autowired
    public void setRocketLauncherPersistenceLayer(final RocketLauncherPersistenceLayer rocketLauncherPersistenceLayer) {
        this.rocketLauncherPersistenceLayer = rocketLauncherPersistenceLayer;
    }

    public List<JsonRocket> getRocketsReadyForLaunch(final int limit) {
        final List<JsonRocket> rocketsToLaunch = new ArrayList<>();
        for (int i=0; i<limit; i++) {
            if (rocketsReadyForLaunch.isEmpty()) {
                break;
            }
            rocketsToLaunch.add(rocketsReadyForLaunch.poll());
        }
        return rocketsToLaunch;
    }

    public JsonRocket launchRocket(final int id) {
        final Rocket rocketToLaunch = rocketLauncherPersistenceLayer.getRocket(id);
        if (rocketToLaunch != null) {
            log.info("Launching rocket with id: {}, created by: {}", rocketToLaunch.getId(), rocketToLaunch.getName() );

            final JsonRocket jsonRocket = toJsonRocket(rocketToLaunch);
            rocketsReadyForLaunch.add(jsonRocket);
            return jsonRocket;
        }
        return null;
    }

    /**
     * Creates a new rocket and returns the created rocket with an unique id.
     */
    public JsonRocket createRocket(final JsonInputRocket rocket) {
        log.info("Creating rocket created by: {}", rocket.getName() );
        return toJsonRocket(rocketLauncherPersistenceLayer.createRocket(toRocket(rocket, 0)));
    }

    /**
     * Get a rocket by ID. If the id does not exist, null is returned
     */
    public JsonRocket getRocket(final int id) {
        log.info("Get rocket by id: {}", id );
        return toJsonRocket(rocketLauncherPersistenceLayer.getRocket(id));
    }

    /**
     * Returns a list of rockets based on name (case insensitive).
     * Empty list is return is no rockets matching the name are found.
     */
    public List<JsonRocket> getRockets(final String name) {
        log.info("Getting rockets by name: '{}'", name );
        return toJsonRockets(rocketLauncherPersistenceLayer.getRockets(name));
    }

    /**
     * Gets all rockets.
     * Empty list is return is no rockets are found.
     */
    public List<JsonRocket> getRockets() {
        log.info("Getting all rockets");
        return toJsonRockets(rocketLauncherPersistenceLayer.getRockets());
    }

    /**
     * Updates an existing rocket by id.
     * If the id exist, the updated rocket are returned, elsewise null is returned.
     */
    public JsonRocket updateRocket(final int id, final JsonInputRocket rocket) {
        log.info("Updating rocket with id: '{}'", id );
        return toJsonRocket(rocketLauncherPersistenceLayer.updateRocket(id, toRocket(rocket, id)));
    }

    /**
     * Deletes a rocket by id.
     * If the id exist the rocket is deleted and returned, elsewise null is returned.
     */
    public JsonRocket deleteRocket(final int id) {
        log.info("Deleting rocket with id: '{}'", id );
        return toJsonRocket(rocketLauncherPersistenceLayer.deleteRocket(id));
    }

    private List<JsonRocket> toJsonRockets(final List<Rocket> rockets) {
        if (rockets == null) {
            return null;
        }
        final List<JsonRocket> jsonRockets = new ArrayList<>();
        for (final Rocket rocket : rockets) {
            jsonRockets.add(toJsonRocket(rocket));
        }
        return jsonRockets;
    }

    private JsonRocket toJsonRocket(final Rocket rocket) {
        if (rocket == null) {
            return null;
        }
        return new JsonRocket(
                rocket.getId(),
                rocket.getName(),
                rocket.getxPosition(),
                rocket.getyVelocity(),
                rocket.getxVelocity(),
                rocket.getColor(),
                rocket.getSize());
    }

    private Rocket toRocket(final JsonInputRocket jsonInputRocket, final int id) {
        if (jsonInputRocket == null) {
            return null;
        }
        return new Rocket.Builder()
                .setName(jsonInputRocket.getName())
                .setxPosition(jsonInputRocket.getxPosition())
                .setyVelocity(jsonInputRocket.getyVelocity())
                .setxVelocity(jsonInputRocket.getxVelocity())
                .setColor(jsonInputRocket.getColor())
                .setSize(jsonInputRocket.getSize())
                .setId(id)
                .build();

    }
}
