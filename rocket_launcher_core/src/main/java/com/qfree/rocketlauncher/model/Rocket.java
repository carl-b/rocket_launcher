package com.qfree.rocketlauncher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Rocket {

    private final int id;
    private final String name;
    private final int xPosition;
    private final int yVelocity;
    private final int xVelocity;
    private final String color;
    private final int size;

    private Rocket(int id, String name, int xPosition, int yVelocity, int xVelocity, String color, int size) {
        this.id = id;
        this.name = name;
        this.xPosition = xPosition;
        this.yVelocity = yVelocity;
        this.xVelocity = xVelocity;
        this.color = color;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xPosition=" + xPosition +
                ", yVelocity=" + yVelocity +
                ", xVelocity=" + xVelocity +
                ", color='" + color + '\'' +
                ", size=" + size +
                '}';
    }

    @JsonIgnore
    public Builder getBuilder() {
        return new Builder()
                .setId(id)
                .setName(name)
                .setColor(color)
                .setSize(size)
                .setxPosition(xPosition)
                .setyVelocity(yVelocity)
                .setxVelocity(xVelocity);
    }


    public static class Builder {
        private int id;
        private String name;
        private int xPosition;
        private int yVelocity;
        private int xVelocity;
        private String color;
        private int size;

        public Builder() { }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setxPosition(int xPosition) {
            this.xPosition = xPosition;
            return this;
        }

        public Builder setyVelocity(int yVelocity) {
            this.yVelocity = yVelocity;
            return this;
        }

        public Builder setxVelocity(int xVelocity) {
            this.xVelocity = xVelocity;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Rocket build() {
            return new Rocket(id, name, xPosition, yVelocity, xVelocity, color, size);
        }
    }
}
