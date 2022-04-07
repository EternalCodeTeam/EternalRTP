package com.eternalcode.bukkit.teleport;

import com.eternalcode.bukkit.shared.BukkitAdapter;
import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import com.eternalcode.randomtp.teleport.TeleportRange;
import org.bukkit.World;

public class WorldBorderRange implements TeleportRange {

    @Override
    public Box getRange(Universe universe) {
        return BukkitAdapter.adapt(universe)
                .map(World::getWorldBorder)
                .map(border -> {
                    Position center = BukkitAdapter.adapt(border.getCenter());
                    double size = border.getSize();

                    return Box.of(center.add(size / 2), center.subtract(size / 2));
                })
                .orElse(Box.of(Position.ZERO, Position.ONE));
    }

}
