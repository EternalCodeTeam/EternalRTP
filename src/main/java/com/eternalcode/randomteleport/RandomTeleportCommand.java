package com.eternalcode.randomteleport;

import com.eternalcode.randomteleport.config.ConfigManager;
import com.eternalcode.randomteleport.config.impl.ButtonDataConfig;
import com.eternalcode.randomteleport.shared.Position;
import com.eternalcode.randomteleport.shared.PositionAdapter;
import com.eternalcode.randomteleport.teleport.TeleportButtonPlace;
import com.eternalcode.randomteleport.teleport.TeleportButtonService;
import com.eternalcode.randomteleport.teleport.TeleportButtonType;
import com.eternalcode.randomteleport.teleport.TeleportService;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Route(name = "randomteleport", aliases = { "rt", "rtp" })
public class RandomTeleportCommand {

    private final TeleportService teleportService;
    private final TeleportButtonService teleportButtonService;

    private final ButtonDataConfig buttonDataConfig;
    private final ConfigManager configManager;

    public RandomTeleportCommand(TeleportService teleportService, TeleportButtonService teleportButtonService, ButtonDataConfig buttonDataConfig, ConfigManager configManager) {
        this.teleportService = teleportService;
        this.teleportButtonService = teleportButtonService;
        this.buttonDataConfig = buttonDataConfig;
        this.configManager = configManager;
    }

    @Execute
    void randomTeleport(Player player, @Arg int radius) {
        this.teleportService.randomTeleportPlayer(player, radius);
    }

    @Execute(route = "setbutton")
    void button(Player player, @Arg String name, @Arg TeleportButtonType type) {
        Location buttonLocation = this.teleportButtonService.setButtonsAround(player);
        Position convertButtonLocation = PositionAdapter.convert(buttonLocation);

        TeleportButtonPlace buttonPlace = new TeleportButtonPlace(name, convertButtonLocation, type);
        this.buttonDataConfig.buttons.put(name, buttonPlace);
        this.configManager.save(this.buttonDataConfig);
    }
}

