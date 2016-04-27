package com.qfree.rocketlauncher.client;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfree.rocketlauncher.model.JsonInputRocket;
import com.qfree.rocketlauncher.model.JsonRocket;
import com.qfree.rocketlauncher.service.RocketLauncherServiceLayer;

@RequestMapping(value = "rocketManagement")
public class RocketManagementController {

    private RocketLauncherServiceLayer rocketLauncherServiceLayer;

    @Autowired
    public void setRocketLauncherServiceLayer(RocketLauncherServiceLayer rocketLauncherServiceLayer) {
        this.rocketLauncherServiceLayer = rocketLauncherServiceLayer;
    }

    /**
     * Creates a new rocket and returns the created rocket with an unique id.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonRocket> createRocket(@RequestBody final JsonInputRocket rocket) {

        ControllerHelper.validateInputRocket(rocket);

        final JsonRocket createdRocket = rocketLauncherServiceLayer.createRocket(rocket);

        return new ResponseEntity<>(createdRocket, ControllerHelper.getResponseHeaders(), HttpStatus.CREATED);
    }



    /**
     * Get a rocket by ID. If the id does not exist, null is returned
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<JsonRocket> getRocket(@PathVariable final String id) {

        ControllerHelper.validateIntParameter(id);

        final JsonRocket rocket = rocketLauncherServiceLayer.getRocket(Integer.parseInt(id));

        ControllerHelper.validateRocketFound(rocket, id);

        return new ResponseEntity<>(rocket, ControllerHelper.getResponseHeaders(), HttpStatus.OK);
    }

    /**
     * Updates an existing rocket by id.
     * If the id exist, the updated rocket are returned, elsewise null is returned.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<JsonRocket> updateRocket(@PathVariable final String id, @RequestBody final JsonInputRocket rocket) {
        ControllerHelper.validateIntParameter(id);
        ControllerHelper.validateInputRocket(rocket);

        final JsonRocket updatedRocket =  rocketLauncherServiceLayer.updateRocket(Integer.parseInt(id), rocket);

        return new ResponseEntity<>(updatedRocket, ControllerHelper.getResponseHeaders(), HttpStatus.OK);
    }

    /**
     * Deletes a rocket by id.
     * If the id exist the rocket is deleted and returned, elsewise null is returned.
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteRocket(@PathVariable final String id) {
        ControllerHelper.validateIntParameter(id);

        final JsonRocket deletedRocket = rocketLauncherServiceLayer.deleteRocket(Integer.parseInt(id));

        ControllerHelper.validateRocketFound(deletedRocket, id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/launch")
    public ResponseEntity<Void> launchRocket(@PathVariable final String id) {
        ControllerHelper.validateIntParameter(id);

        JsonRocket rocketAddedToQueue = rocketLauncherServiceLayer.launchRocket(Integer.parseInt(id));

        ControllerHelper.validateRocketFound(rocketAddedToQueue, id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Returns a list of rockets based on name (case insensitive).
     * Empty list is return is no rockets matching the name are found.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/listByName")
    public ResponseEntity<List<JsonRocket>> getRockets(@RequestParam final String name) {

        final List<JsonRocket> rockets = rocketLauncherServiceLayer.getRockets(name);

        return new ResponseEntity<>(rockets, ControllerHelper.getResponseHeaders(), HttpStatus.OK);
    }

    /**
     * Get all rockets.
     * Empty list is return is no rockets are found.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public ResponseEntity<List<JsonRocket>> getRockets() {
        final List<JsonRocket> rockets = rocketLauncherServiceLayer.getRockets();

        return new ResponseEntity<>(rockets, ControllerHelper.getResponseHeaders(), HttpStatus.OK);
    }

}
