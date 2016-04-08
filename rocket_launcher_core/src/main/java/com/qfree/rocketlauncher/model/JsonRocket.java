package com.qfree.rocketlauncher.model;

public class JsonRocket extends JsonInputRocket {

    private int id;

    public JsonRocket() {
    }

    public JsonRocket(int id, String name, int xPosition, int yVelocity, int xVelocity, String color, int size) {
        super(name, xPosition, yVelocity, xVelocity, color, size);
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
