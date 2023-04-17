package com.eternalcode.randomteleport.teleport;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;

public class TeleportButtonService {

    private static final BlockFace[] FACES = new BlockFace[]{
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST,
    };

    public Location setButtonsAround(Player player) {
        Block targetBlock = player.getTargetBlock(null, 100);

        if (targetBlock.getType() == Material.SPONGE) {
            for (BlockFace face : FACES) {
                Block adjacentBlock = targetBlock.getRelative(face);

                if (adjacentBlock.getType() == Material.AIR) {
                    this.setStoneButtonOnAdjacentBlock(adjacentBlock, face);
                }
            }
        }

        return targetBlock.getLocation();
    }

    private void setStoneButtonOnAdjacentBlock(Block adjacentBlock, BlockFace face) {
        adjacentBlock.setType(Material.STONE_BUTTON);
        BlockData blockData = adjacentBlock.getBlockData();

        if (blockData instanceof Directional directional) {
            directional.setFacing(face);
            adjacentBlock.setBlockData(directional);
        }
    }

}

