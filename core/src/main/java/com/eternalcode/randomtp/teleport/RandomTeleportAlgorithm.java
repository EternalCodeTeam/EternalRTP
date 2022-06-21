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

        return new Position(universe, random.nextDouble(min.getX(), max.getX()), 0, random.nextDouble(min.getZ(), max.getZ()));
    }

}
