package com.eternalcode.randomtp.config;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import org.junit.jupiter.api.Test;
import panda.std.Result;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionComposerTest {

    private final PositionComposer composer = new PositionComposer();

    @Test
    public void serializeTest() {
        Result<String, Exception> result = composer.serialize(new Position(Universe.of("world"), -1.5D, 2.0D, 3.4, 2.7F, 1.4F));

        assertEquals("world:-1.500000:2.000000:3.400000:2.700000:1.400000", result.get());
    }

    @Test
    public void test() {
        Result<Position, Exception> deserialize = composer.deserialize("world:-1.500000:2.000000:3.400000:2.700000:1.400000");
        Position position = deserialize.get();

        assertEquals("world", position.getUniverse().getName());
        assertEquals(-1.5D, position.getX());
        assertEquals(2.0D, position.getY());
        assertEquals(3.4D, position.getZ());
        assertEquals(2.7F, position.getYaw());
        assertEquals(1.4F, position.getPitch());
    }

}
