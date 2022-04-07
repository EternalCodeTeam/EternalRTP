package com.eternalcode.bukkit.shared;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Optional;

public class BukkitAdapter {

    public static Universe adapt(World world) {
        if (world == null) {
            return Universe.none();
        }

        return Universe.create(world.getName());
    }

    public static Optional<World> adapt(Universe universe) {
        if (!universe.isExisting()) {
            return Optional.empty();
        }

        return Optional.ofNullable(Bukkit.getWorld(universe.getName()));
    }

    public static Position adapt(Location location) {
        return new Position(adapt(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public static Location adapt(Position position) {
        return new Location(adapt(position.getUniverse()).orElse(null), position.getX(), position.getY(), position.getZ(), position.getYaw(), position.getPitch());
    }

}
