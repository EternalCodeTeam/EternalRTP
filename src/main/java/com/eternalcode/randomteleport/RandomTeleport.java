package com.eternalcode.randomteleport;

import com.eternalcode.randomteleport.config.ConfigManager;
import com.eternalcode.randomteleport.config.impl.ButtonDataConfig;
import com.eternalcode.randomteleport.teleport.TeleportButtonService;
import com.eternalcode.randomteleport.teleport.TeleportController;
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

        ConfigManager configManager = new ConfigManager(this.getDataFolder());
        ButtonDataConfig buttonDataConfig = configManager.load(new ButtonDataConfig());

        TeleportService teleportService = new TeleportService();
        TeleportButtonService teleportButtonService = new TeleportButtonService(buttonDataConfig, configManager);

        this.liteCommands = LiteBukkitAdventurePlatformFactory.builder(server, "randomteleport", this.audienceProvider)
                .argument(Player.class, new BukkitPlayerArgument<>(this.getServer(), "cant find player"))
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>("only for console"))

                .commandInstance(new RandomTeleportCommand(teleportService, teleportButtonService, buttonDataConfig, configManager))

                .register();

        server.getPluginManager().registerEvents(new TeleportController(), this);
    }

    @Override
    public void onDisable() {
        if (this.liteCommands != null) {
            this.liteCommands.getPlatform().unregisterAll();
        }
    }

}
