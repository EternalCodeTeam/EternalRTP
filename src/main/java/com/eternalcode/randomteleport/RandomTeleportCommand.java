package com.eternalcode.randomteleport;

import com.eternalcode.randomteleport.teleport.TeleportButtonService;
import com.eternalcode.randomteleport.teleport.TeleportButtonType;
import com.eternalcode.randomteleport.teleport.TeleportService;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.entity.Player;

@Route(name = "randomteleport", aliases = {"rtp"})
public class RandomTeleportCommand {

    private final TeleportService teleportService;
    private final TeleportButtonService teleportButtonService;

    public RandomTeleportCommand(TeleportService teleportService, TeleportButtonService teleportButtonService) {
        this.teleportService = teleportService;
        this.teleportButtonService = teleportButtonService;
    }

    @Execute
    void randomTeleport(Player player, @Arg int radius) {
        this.teleportService.randomTeleportPlayer(player, radius);
    }

    @Execute(route = "setbutton")
    void button(Player player, @Arg TeleportButtonType type, @Arg String name) {
        this.teleportButtonService.createButtonArea(player, name, type);
    }
}

