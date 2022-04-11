package com.eternalcode.randomtp.bukkit.teleport;

import com.eternalcode.randomtp.bukkit.shared.BukkitProvider;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.teleport.TeleportPositionCorrector;
import io.papermc.lib.PaperLib;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class WorldTeleportPositionCorrector implements TeleportPositionCorrector {

    @Override
    public CompletableFuture<Position> correctPosition(Position position) {
        CompletableFuture<Position> future = new CompletableFuture<>();
        Optional<World> optional = BukkitProvider.convert(position.getUniverse());

        if (optional.isEmpty()) {
            return CompletableFuture.completedFuture(position);
        }

        World world = optional.get();

        int x = position.getBlockX() >> 4;
        int z = position.getBlockZ() >> 4;

        PaperLib.getChunkAtAsync(world, x, z)
                .thenApply(chunk -> world.getHighestBlockAt(position.getBlockX(), position.getBlockZ()).getLocation())
                .thenApply(BukkitProvider::convert)
                .thenApply(pos -> pos.withY(pos.getY() + 1))
                .thenAccept(future::complete);

        return future;
    }

}
