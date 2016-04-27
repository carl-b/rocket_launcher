package com.qfree.rocketlauncher.model;



public class JsonInputRocket {

    private String name;
    private int xPosition;
    private int yVelocity;
    private int xVelocity;
    private int color;
    private int size;

    public JsonInputRocket() {
    }

    public JsonInputRocket(String name, int xPosition, int yVelocity, int xVelocity, int color, int size) {
        this.name = name;
        this.xPosition = xPosition;
        this.yVelocity = yVelocity;
        this.xVelocity = xVelocity;
        this.color = color;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
