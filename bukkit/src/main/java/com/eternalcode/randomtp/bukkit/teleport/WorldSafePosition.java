package com.eternalcode.randomtp.bukkit.teleport;

import com.eternalcode.randomtp.bukkit.shared.BukkitProvider;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.teleport.TeleportCorrector;
import io.papermc.lib.PaperLib;
import org.bukkit.World;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class WorldSafePosition implements TeleportCorrector {

    @Override
    public CompletableFuture<Position> correct(Position position) {
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
                .thenApply(pos -> pos.withY(pos.getY()))
                .thenAccept(future::complete);

        return future;
    }

}
