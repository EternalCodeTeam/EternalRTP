package com.eternalcode.randomtp.shared;

import java.util.Objects;

public class Box {

    private final Position min;
    private final Position max;

    private Box(Position min, Position max) {
        this.min = min;
        this.max = max;
    }

    public Position getMin() {
        return this.min;
    }

    public Position getMax() {
        return this.max;
    }

    public boolean contains(Position pos) {
        return pos.getX() >= this.min.getX() && pos.getX() <= this.max.getX()
                && pos.getY() >= this.min.getY() && pos.getY() <= this.max.getY()
                && pos.getZ() >= this.min.getZ() && pos.getZ() <= this.max.getZ();
    }

    public static Box of(Position min, Position max) {
        double minX = Math.min(min.getX(), max.getX());
        double minY = Math.min(min.getY(), max.getY());
        double minZ = Math.min(min.getZ(), max.getZ());
        double maxX = Math.max(min.getX(), max.getX());
        double maxY = Math.max(min.getY(), max.getY());
        double maxZ = Math.max(min.getZ(), max.getZ());

        return new Box(new Position(minX, minY, minZ), new Position(maxX, maxY, maxZ));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Box box)) {
            return false;
        }

        return this.min.equals(box.min) && this.max.equals(box.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.min, this.max);
    }

    @Override
    public String toString() {
        return "Box{" +
                "min=" + this.min +
                ", max=" + this.max +
                '}';
    }

}
