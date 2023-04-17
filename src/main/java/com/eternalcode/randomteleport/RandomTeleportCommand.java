package com.eternalcode.randomteleport;

import com.eternalcode.randomteleport.teleport.TeleportService;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.entity.Player;

@Route(name = "randomteleport", aliases = { "rt", "rtp" })
public class RandomTeleportCommand {

    private final TeleportService teleportService;

    public RandomTeleportCommand(TeleportService teleportService) {
        this.teleportService = teleportService;
    }

    @Execute
    void randomTeleport(Player player, @Arg int radius) {
        this.teleportService.randomTeleportPlayer(player, radius);
    }
}
