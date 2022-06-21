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
        return min;
    }

    public Position getMax() {
        return max;
    }

    public boolean contains(Position pos) {
        return pos.getX() >= min.getX() && pos.getX() <= max.getX()
                && pos.getY() >= min.getY() && pos.getY() <= max.getY()
                && pos.getZ() >= min.getZ() && pos.getZ() <= max.getZ();
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

        return min.equals(box.min) && max.equals(box.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public String toString() {
        return "Box{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

}
