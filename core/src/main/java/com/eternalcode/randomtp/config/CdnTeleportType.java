package com.eternalcode.randomtp.config;

import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Universe;
import com.eternalcode.randomtp.teleport.game.TeleportType;
import net.dzikoysk.cdn.entity.Contextual;

@Contextual
public class CdnTeleportType implements TeleportType {

    public String name = "normal";
    public BlockType coreType = BlockType.of("SPONGE");
    public BlockType buttonType = BlockType.of("STONE_BUTTON");
    public Universe universe = Universe.of("world");
    public int radius = 5;
    public boolean isGroup = false;
    public boolean requiredPlate = false;
    public BlockType plateBlock = BlockType.none();

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BlockType getCoreType() {
        return this.coreType;
    }

    @Override
    public BlockType getButtonBlock() {
        return this.buttonType;
    }

    @Override
    public Universe getUniverse() {
        return this.universe;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public boolean isGroup() {
        return this.isGroup;
    }

    @Override
    public boolean requiredPlate() {
        return this.requiredPlate;
    }

    @Override
    public BlockType getPlateBlock() {
        return this.plateBlock;
    }

}
