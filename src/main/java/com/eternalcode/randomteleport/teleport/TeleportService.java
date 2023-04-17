package com.eternalcode.randomteleport.teleport;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportService {

    public void randomTeleportPlayers(Player[] players, int radius) {
        for (Player player : players) {
            this.randomTeleportPlayer(player, radius);
        }
    }

    public void randomTeleportPlayer(Player player, int radius) {
        World world = player.getWorld();

        Location safeLocation = this.getSafeLocation(world, radius);

        player.teleport(safeLocation);
    }

    public Location getSafeLocation(World world, int radius) {
        int maxTries = 100;
        int currentTry = 0;

        while (currentTry < maxTries) {
            int x = (int) (Math.random() * (radius * 2 + 1) - radius);
            int z = (int) (Math.random() * (radius * 2 + 1) - radius);

            Location location = world.getHighestBlockAt(x, z).getLocation();
            location.add(0, 1, 0);

            System.out.println("Trying location: " + location);

            if (this.isSafeLocation(location)) {
                return location;
            }

            currentTry++;
        }

        return world.getSpawnLocation();
    }

    public boolean isSafeLocation(Location location) {
        if (location == null || location.getWorld() == null) {
            return false;
        }

        List<Material> unsafeBlocks = List.of(
                Material.LAVA,
                Material.WATER,
                Material.CACTUS,
                Material.FIRE,
                Material.COBWEB
        );

        World world = location.getWorld();
        Block block = world.getBlockAt(location);
        Block blockAbove = world.getBlockAt(location.clone().add(0, 1, 0));
        Block blockBelow = world.getBlockAt(location.clone().add(0, -1, 0));

        if (this.hasNearbyUnsafeBlock(location, unsafeBlocks)) {
            return false;
        }

        if (unsafeBlocks.contains(blockBelow.getType())) {
            return false;
        }

        if (block.getType().isSolid() && blockAbove.getType().isSolid()) {
            return false;
        }

        if (!blockBelow.getType().isSolid()) {
            return false;
        }

        switch (world.getEnvironment()) {
            case NORMAL, THE_END -> {
                return true;
            }
            case NETHER -> {
                return !(location.getY() > 127);
            }
            default -> {
                return false;
            }
        }
    }

    private boolean hasNearbyUnsafeBlock(Location location, List<Material> unsafeBlocks) {
        World world = location.getWorld();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block[] nearbyBlocks = new Block[] {
                world.getBlockAt(x - 1, y, z),
                world.getBlockAt(x + 1, y, z),
                world.getBlockAt(x, y - 1, z),
                world.getBlockAt(x, y + 1, z),
                world.getBlockAt(x, y, z - 1),
                world.getBlockAt(x, y, z + 1)
        };

        for (Block nearbyBlock : nearbyBlocks) {
            if (unsafeBlocks.contains(nearbyBlock.getType())) {
                return true;
            }
        }

        return false;
    }

}
