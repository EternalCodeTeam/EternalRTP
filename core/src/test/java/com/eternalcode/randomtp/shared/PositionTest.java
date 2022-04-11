package com.eternalcode.randomtp.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    @Test
    public void testNormalizeSimple() {
        Position a = new Position(100, 100, 100);
        Position normalized = a.normalize();

        assertEquals(0.5773502691896257D, normalized.getX());
        assertEquals(0.5773502691896257D, normalized.getY());
        assertEquals(0.5773502691896257D, normalized.getZ());
    }

    @Test
    public void testNormalize() {
        Position a = new Position(10, 1, 1);
        Position normalized = a.normalize();

        assertEquals(0.9901475429766744D, normalized.getX());
        assertEquals(0.09901475429766744D, normalized.getY());
        assertEquals(0.09901475429766744D, normalized.getZ());
    }

    @Test
    public void testDistanceZero() {
        Position a = new Position(1, 1, 1);
        Position b = new Position(1, 1, 1);

        assertEquals(0, a.distance(b));
    }

    @Test
    public void testDistanceOne() {
        Position a = new Position(0, 0, 0);
        Position b = new Position(1, 0, 0);

        assertEquals(1, a.distance(b));
    }

    @Test
    public void testDistanceSqrt2() {
        Position a = new Position(0, 0, 0);
        Position b = new Position(1, 1, 0);

        assertEquals(Math.sqrt(2), a.distance(b));
    }

    @Test
    public void testDistanceSqrt3() {
        Position a = new Position(0, 0, 0);
        Position b = new Position(1, 1, 1);

        assertEquals(Math.sqrt(3), a.distance(b));
    }

    @Test
    public void testWithYaw() {
        Position position = new Position(Universe.NONE, 1, 2, 3, 4F, 5F);
        Position newPos = position.withYaw(6F);

        assertEquals(Universe.NONE, newPos.getUniverse());
        assertEquals(1, newPos.getX());
        assertEquals(2, newPos.getY());
        assertEquals(3, newPos.getZ());
        assertEquals(6F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testWithPitch() {
        Position position = new Position(Universe.NONE, 1, 2, 3, 4F, 5F);
        Position newPos = position.withPitch(6F);

        assertEquals(Universe.NONE, newPos.getUniverse());
        assertEquals(1, newPos.getX());
        assertEquals(2, newPos.getY());
        assertEquals(3, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(6F, newPos.getPitch());
    }

    @Test
    public void testWithYawAndPitch() {
        Position position = new Position(Universe.NONE, 1, 2, 3, 4F, 5F);
        Position newPos = position.withYaw(6F).withPitch(7F);

        assertEquals(Universe.NONE, newPos.getUniverse());
        assertEquals(1, newPos.getX());
        assertEquals(2, newPos.getY());
        assertEquals(3, newPos.getZ());
        assertEquals(6F, newPos.getYaw());
        assertEquals(7F, newPos.getPitch());
    }

    @Test
    public void testWithX() {
        Position position = new Position(Universe.NONE, 1, 2, 3, 4F, 5F);
        Position newPos = position.withX(6);

        assertEquals(Universe.NONE, newPos.getUniverse());
        assertEquals(6, newPos.getX());
        assertEquals(2, newPos.getY());
        assertEquals(3, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testWithY() {
        Position position = new Position(Universe.NONE, 1, 2, 3, 4F, 5F);
        Position newPos = position.withY(6);

        assertEquals(Universe.NONE, newPos.getUniverse());
        assertEquals(1, newPos.getX());
        assertEquals(6, newPos.getY());
        assertEquals(3, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testWithZ() {
        Position position = new Position(Universe.NONE, 1, 2, 3, 4F, 5F);
        Position newPos = position.withZ(6);

        assertEquals(Universe.NONE, newPos.getUniverse());
        assertEquals(1, newPos.getX());
        assertEquals(2, newPos.getY());
        assertEquals(6, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testWithUniverse() {
        Position position = new Position(Universe.NONE, 1, 2, 3, 4F, 5F);
        Position newPos = position.withUniverse(Universe.WORLD);

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(1, newPos.getX());
        assertEquals(2, newPos.getY());
        assertEquals(3, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testAdd() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.add(1);

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(2, newPos.getX());
        assertEquals(3, newPos.getY());
        assertEquals(4, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testAddXYZ() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.add(1.0, 2.0, 3.0);

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(2, newPos.getX());
        assertEquals(4, newPos.getY());
        assertEquals(6, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testAddPosition() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.add(new Position(Universe.NONE, 1, 2, 3, 0F, 0F));

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(2, newPos.getX());
        assertEquals(4, newPos.getY());
        assertEquals(6, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testSubtract() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.subtract(1);

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(0, newPos.getX());
        assertEquals(1, newPos.getY());
        assertEquals(2, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testSubtractXYZ() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.subtract(1.0, 2.0, 3.0);

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(0, newPos.getX());
        assertEquals(0, newPos.getY());
        assertEquals(0, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testSubtractPosition() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.subtract(new Position(Universe.NONE, 1, 2, 3, 0F, 0F));

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(0, newPos.getX());
        assertEquals(0, newPos.getY());
        assertEquals(0, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testMultiply() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.multiply(2);

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(2, newPos.getX());
        assertEquals(4, newPos.getY());
        assertEquals(6, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testMultiplyXYZ() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.multiply(2.0, 3.0, 4.0);

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(2, newPos.getX());
        assertEquals(6, newPos.getY());
        assertEquals(12, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testMultiplyPosition() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.multiply(new Position(Universe.NONE, 2, 3, 4, 0F, 0F));

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(2, newPos.getX());
        assertEquals(6, newPos.getY());
        assertEquals(12, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testDivide() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.divide(2);

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(0.5, newPos.getX());
        assertEquals(1, newPos.getY());
        assertEquals(1.5, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testDivideXYZ() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.divide(2.0, 2.0, 4.0);

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(0.5, newPos.getX());
        assertEquals(1, newPos.getY());
        assertEquals(0.75, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }

    @Test
    public void testDividePosition() {
        Position position = new Position(Universe.WORLD, 1, 2, 3, 4F, 5F);
        Position newPos = position.divide(new Position(Universe.NONE, 2, 2, 4, 0F, 0F));

        assertEquals(Universe.WORLD, newPos.getUniverse());
        assertEquals(0.5, newPos.getX());
        assertEquals(1, newPos.getY());
        assertEquals(0.75, newPos.getZ());
        assertEquals(4F, newPos.getYaw());
        assertEquals(5F, newPos.getPitch());
    }


}
