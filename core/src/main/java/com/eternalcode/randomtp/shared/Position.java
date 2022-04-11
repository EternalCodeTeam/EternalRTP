package com.eternalcode.randomtp.shared;

import java.util.Objects;

public class Position {

    public static final Position ZERO = new Position(0, 0, 0);
    public static final Position ONE = new Position(1, 1, 1);

    private final Universe universe;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public Position(Universe universe, double x, double y, double z, float yaw, float pitch) {
        this.universe = universe;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Position(Universe universe, double x, double y, double z) {
        this(universe, x, y, z, 0, 0);
    }

    public Position(double x, double y, double z) {
        this(Universe.none(), x, y, z);
    }

    public Position(double value) {
        this(Universe.none(), value, value, value);
    }

    public Universe getUniverse() {
        return universe;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public int getBlockX() {
        return (int) Math.floor(x);
    }

    public int getBlockY() {
        return (int) Math.floor(y);
    }

    public int getBlockZ() {
        return (int) Math.floor(z);
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public Position add(Position position) {
        return new Position(this.universe, this.x + position.x, this.y + position.y, this.z + position.z, this.yaw, this.pitch);
    }

    public Position add(double x, double y, double z) {
        return new Position(this.universe, this.x + x, this.y + y, this.z + z, this.yaw, this.pitch);
    }

    public Position add(double value) {
        return new Position(this.universe, this.x + value, this.y + value, this.z + value, this.yaw, this.pitch);
    }

    public Position subtract(Position position) {
        return new Position(this.universe, this.x - position.x, this.y - position.y, this.z - position.z, this.yaw, this.pitch);
    }

    public Position subtract(double x, double y, double z) {
        return new Position(this.universe, this.x - x, this.y - y, this.z - z, this.yaw, this.pitch);
    }

    public Position subtract(double value) {
        return new Position(this.universe, this.x - value, this.y - value, this.z - value, this.yaw, this.pitch);
    }

    public Position multiply(Position position) {
        return new Position(this.universe, this.x * position.x, this.y * position.y, this.z * position.z, this.yaw, this.pitch);
    }

    public Position multiply(double x, double y, double z) {
        return new Position(this.universe, this.x * x, this.y * y, this.z * z, this.yaw, this.pitch);
    }

    public Position multiply(double value) {
        return new Position(this.universe, this.x * value, this.y * value, z * value, this.yaw, this.pitch);
    }

    public Position divide(Position position) {
        return new Position(this.universe, this.x / position.x, this.y / position.y, this.z / position.z, this.yaw, this.pitch);
    }

    public Position divide(double x, double y, double z) {
        return new Position(this.universe, this.x / x, this.y / y, this.z / z, this.yaw, this.pitch);
    }

    public Position divide(double value) {
        return new Position(this.universe, this.x / value, this.y / value, this.z / value, this.yaw, this.pitch);
    }

    public Position withUniverse(Universe universe) {
        return new Position(universe, this.x, this.y, this.z, this.yaw, this.pitch);
    }

    public Position withX(double x) {
        return new Position(this.universe, x, this.y, this.z, this.yaw, this.pitch);
    }

    public Position withY(double y) {
        return new Position(this.universe, this.x, y, this.z, this.yaw, this.pitch);
    }

    public Position withZ(double z) {
        return new Position(this.universe, this.x, this.y, z, this.yaw, this.pitch);
    }

    public Position withYaw(float yaw) {
        return new Position(this.universe, this.x, this.y, this.z, yaw, this.pitch);
    }

    public Position withPitch(float pitch) {
        return new Position(this.universe, this.x, this.y, this.z, this.yaw, pitch);
    }

    public Position normalize() {
        double length = Math.sqrt(x * x + y * y + z * z);
        return new Position(this.universe, x / length, y / length, z / length, this.yaw, this.pitch);
    }

    public double distance(Position position) {
        return Math.sqrt((x - position.x) * (x - position.x) + (y - position.y) * (y - position.y) + (z - position.z) * (z - position.z));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Position position)) {
            return false;
        }

        return Double.compare(position.x, x) == 0 && Double.compare(position.y, y) == 0 && Double.compare(position.z, z) == 0 && Float.compare(position.yaw, yaw) == 0 && Float.compare(position.pitch, pitch) == 0 && universe.equals(position.universe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universe, x, y, z, yaw, pitch);
    }

}
