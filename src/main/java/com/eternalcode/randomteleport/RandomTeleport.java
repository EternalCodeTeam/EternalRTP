package com.eternalcode.randomteleport;

import com.eternalcode.randomteleport.teleport.TeleportService;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.adventure.platform.LiteBukkitAdventurePlatformFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import dev.rollczi.litecommands.bukkit.tools.BukkitPlayerArgument;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomTeleport extends JavaPlugin {

    private AudienceProvider audienceProvider;
    private LiteCommands<CommandSender> liteCommands;

    @Override
    public void onEnable() {
        Server server = this.getServer();

        this.audienceProvider = BukkitAudiences.create(this);

        TeleportService teleportService = new TeleportService();

        this.liteCommands = LiteBukkitAdventurePlatformFactory.builder(server, "randomteleport", this.audienceProvider)
                .argument(Player.class, new BukkitPlayerArgument<>(this.getServer(), "cant find player"))
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>("only for console"))

                .commandInstance(new RandomTeleportCommand(teleportService))

                .register();
    }

    @Override
    public void onDisable() {
        if (this.liteCommands != null) {
            this.liteCommands.getPlatform().unregisterAll();
        }
    }

}
