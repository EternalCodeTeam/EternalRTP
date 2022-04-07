package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeleportAlgorithmTest {

    private final Box range = Box.of(new Position(-10), new Position(10));

    @Test
    public void test() {
        TeleportAlgorithm algorithm = new RandomTeleportAlgorithm();

        for (int test = 0; test < 1000; test++) {
            Position position = algorithm.createPosition(range, Universe.NONE);

            assertTrue(range.contains(position));
        }
    }

}
