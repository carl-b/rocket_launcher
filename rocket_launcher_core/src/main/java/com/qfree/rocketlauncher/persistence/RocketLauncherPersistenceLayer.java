package com.qfree.rocketlauncher.persistence;

import java.util.List;

import com.qfree.rocketlauncher.model.Rocket;

public interface RocketLauncherPersistenceLayer {

    /**
     * Creates a new rocket and returns the created rocket with an unique id.
     */
    public Rocket createRocket(Rocket rocket);

    /**
     * Get a rocket by ID. If the id does not exist, null is returned
     */
    public Rocket getRocket(int id);

    /**
     * Returns a list of rockets based on name (case insensitive).
     * Empty list is return is no rockets matching the name are found.
     */
    public List<Rocket> getRockets(String name);

    /**
     * Gets all rockets.
     * Empty list is return is no rockets are found.
     */
    public List<Rocket> getRockets();

    /**
     * Updates an existing rocket by id.
     * If the id exist, the updated rocket are returned, elsewise null is returned.
     */
    public Rocket updateRocket(int id, Rocket rocket);

    /**
     * Deletes a rocket by id.
     * If the id exist the rocket is deleted and returned, elsewise null is returned.
     */
    public Rocket deleteRocket(int id);

}
