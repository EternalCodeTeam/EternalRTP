package com.eternalcode.randomtp.bukkit.shared;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class BukkitConverter {

    public static Universe convert(World world) {
        if (world == null) {
            return Universe.none();
        }

        return Universe.create(world.getName());
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
            return convert(player);
        }

        return Optional.empty();
    }

    public static Optional<Profile> convert(Player player) {
        return Optional.of(new PlayerBukkitProfile(Bukkit.getServer(), player.getUniqueId()));
    }

}
