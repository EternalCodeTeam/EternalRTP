package com.eternalcode.randomtp.bukkit.shared;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.BlockState;
import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.shared.Universe;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

public class BukkitProvider {

    public static Universe convert(World world) {
        if (world == null) {
            return Universe.none();
        }

        return Universe.of(world.getName());
    }

    public static Optional<World> convert(Universe universe) {
        if (!universe.isExisting()) {
            return Optional.empty();
        }

        return Optional.ofNullable(Bukkit.getWorld(universe.getName()));
    }

    public static Position convert(Location location) {
        return new Position(convert(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public static Location convert(Position position) {
        return new Location(convert(position.getUniverse()).orElse(null), position.getX(), position.getY(), position.getZ(), position.getYaw(), position.getPitch());
    }

    public static Optional<Profile> convert(Object sender) {
        if (sender instanceof Player player) {
            return Optional.of(convert(player));
        }

        return Optional.empty();
    }

    public static Profile convert(Player player) {
        return new BukkitPlayerProfile(Bukkit.getServer(), player.getUniqueId());
    }

    public static BlockType convert(Material material) {
        return BlockType.of(material.name());
    }

    public static Material convert(BlockType blockType) {
        if (blockType.isNone()) {
            return Material.AIR;
        }

        return Material.valueOf(blockType.getName());
    }

    public static BlockState convert(Block block) {
        return new BlockState(convert(block.getLocation()), convert(block.getType()));
    }

    public static Scheduler createScheduler(Plugin plugin) {
        return new BukkitScheduler(plugin);
    }

    public static Game createGame() {
        return new BukkitGame();
    }

}
