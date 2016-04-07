package com.qfree.rocketlauncher.model;

import com.qfree.rocketlauncher.tools.AtomicCounter;

public class Rocket {

    private final long id;
    private final String name;
    private final int xPosition;
    private final int yVelocity;
    private final int xVelocity;
    private final String color;
    private final int size;

    private Rocket(long id, String name, int xPosition, int yVelocity, int xVelocity, String color, int size) {
        this.id = id;
        this.name = name;
        this.xPosition = xPosition;
        this.yVelocity = yVelocity;
        this.xVelocity = xVelocity;
        this.color = color;
        this.size = size;
    }

    public long getId() {
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

    public static class Builder {
        private long id;
        private String name;
        private int xPosition;
        private int yVelocity;
        private int xVelocity;
        private String color;
        private int size;

        public Builder() {
            id = AtomicCounter.getNext();
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
