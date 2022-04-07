package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;

import java.util.Random;

public class RandomTeleportAlgorithm implements TeleportAlgorithm {

    private final Random random = new Random();

    @Override
    public Position createPosition(Box box, Universe universe) {
        Position min = box.getMin();
        Position max = box.getMax();

        int x = (int) (random.nextDouble() * (max.getX() - min.getX()) + min.getX());
        int z = (int) (random.nextDouble() * (max.getZ() - min.getZ()) + min.getZ());

        return new Position(universe, x, 0, z);
    }

}
