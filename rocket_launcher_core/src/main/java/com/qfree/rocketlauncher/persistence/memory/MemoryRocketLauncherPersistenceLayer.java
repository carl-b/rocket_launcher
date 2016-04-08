package com.qfree.rocketlauncher.persistence.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qfree.rocketlauncher.model.Rocket;
import com.qfree.rocketlauncher.persistence.RocketLauncherPersistenceLayer;
import com.qfree.rocketlauncher.tools.AtomicCounter;

public class MemoryRocketLauncherPersistenceLayer implements RocketLauncherPersistenceLayer {


    private Map<Integer, Rocket> rockets = new HashMap<>();

    public MemoryRocketLauncherPersistenceLayer() {

    }

    @Override
    public Rocket createRocket(final Rocket rocket) {
        int rocketId = AtomicCounter.getNext();
        final Rocket rocketWithId = rocket.getBuilder().setId(rocketId).build();
        rockets.put(rocketId, rocketWithId);
        return rocketWithId;
    }

    @Override
    public Rocket getRocket(int id) {
        return rockets.get(id);
    }

    @Override
    public List<Rocket> getRockets(String name) {
        final List<Rocket> rocketsMatchingName = new ArrayList<>();
        for (final Rocket aRocket : rockets.values()) {
            if (aRocket.getName().equalsIgnoreCase(name)) {
                rocketsMatchingName.add(aRocket);
            }
        }

        return rocketsMatchingName;
    }

    @Override
    public List<Rocket> getRockets() {
        return new ArrayList<>(rockets.values());
    }

    @Override
    public Rocket updateRocket(int id, Rocket rocket) {
        if (rockets.containsKey(id)) {
            rockets.put(id, rocket);
            return rocket;
        }
        return null;
    }

    @Override
    public Rocket deleteRocket(int id) {
        return rockets.remove(id);
    }
}
