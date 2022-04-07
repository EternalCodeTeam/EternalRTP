package com.eternalcode.bukkit.shared;

import com.eternalcode.bukkit.shared.BukkitAdapter;
import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import io.papermc.lib.PaperLib;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitProfile implements Profile {

    private final Server server;
    private final UUID uuid;

    public BukkitProfile(Server server, UUID uuid) {
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
    public void teleport(Position position) {
        Player player = this.server.getPlayer(this.uuid);

        if (player == null) {
            return;
        }

        PaperLib.teleportAsync(player, BukkitAdapter.adapt(position));
    }

}
