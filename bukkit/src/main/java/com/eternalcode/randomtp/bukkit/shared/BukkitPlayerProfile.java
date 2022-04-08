package com.eternalcode.randomtp.bukkit.shared;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import io.papermc.lib.PaperLib;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class BukkitPlayerProfile implements Profile {

    private final Server server;
    private final UUID uuid;

    BukkitPlayerProfile(Server server, UUID uuid) {
        this.server = server;
        this.uuid = uuid;
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public Position getPosition() {
        Player player = this.server.getPlayer(this.uuid);

        if (player == null) {
            return Position.ZERO;
        }

        return BukkitProvider.convert(player.getLocation());
    }

    @Override
    public Optional<Position> getTargetPosition() {
        Player player = this.server.getPlayer(this.uuid);

        if (player == null) {
            return Optional.empty();
        }

        Block blockExact = player.getTargetBlockExact(8);

        if (blockExact == null) {
            return Optional.empty();
        }

        return Optional.of(BukkitProvider.convert(blockExact.getLocation()));
    }

    @Override
    public Universe getUniverse() {
        Player player = this.server.getPlayer(this.uuid);

        if (player == null) {
            return Universe.NONE;
        }

        return BukkitProvider.convert(player.getWorld());
    }

    @Override
    public CompletableFuture<Boolean> teleport(Position position) {
        Player player = this.server.getPlayer(this.uuid);

        if (player == null) {
            return CompletableFuture.completedFuture(false);
        }

        return PaperLib.teleportAsync(player, BukkitProvider.convert(position));
    }

}
