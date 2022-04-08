package com.eternalcode.randomtp.bukkit.shared;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import io.papermc.lib.PaperLib;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class PlayerBukkitProfile implements Profile {

    private final Server server;
    private final UUID uuid;

    PlayerBukkitProfile(Server server, UUID uuid) {
        this.server = server;
        this.uuid = uuid;
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public Universe getUniverse() {
        Player player = this.server.getPlayer(this.uuid);

        if (player == null) {
            return Universe.NONE;
        }

        return Universe.create(player.getWorld().getName());
    }

    @Override
    public CompletableFuture<Boolean> teleport(Position position) {
        Player player = this.server.getPlayer(this.uuid);

        if (player == null) {
            return CompletableFuture.completedFuture(false);
        }

        return PaperLib.teleportAsync(player, BukkitConverter.convert(position));
    }

}
