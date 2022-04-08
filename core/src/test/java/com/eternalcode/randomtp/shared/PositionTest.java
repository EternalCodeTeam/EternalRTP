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

}
