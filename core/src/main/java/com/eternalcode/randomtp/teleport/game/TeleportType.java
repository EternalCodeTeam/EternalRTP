package com.eternalcode.randomtp.teleport.game;

import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Universe;

public interface TeleportType {

    String getName();

    BlockType getCoreType();

    BlockType getButtonBlock();

    Universe getUniverse();

    double getRadius();

    boolean isGroup();

    boolean requiredPlate();

    BlockType getPlateBlock();

}
