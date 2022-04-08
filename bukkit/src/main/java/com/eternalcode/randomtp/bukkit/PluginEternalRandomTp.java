package com.eternalcode.randomtp.bukkit;

import com.eternalcode.randomtp.bukkit.shared.BukkitConverter;
import com.eternalcode.randomtp.bukkit.shared.SchedulerBukkit;
import com.eternalcode.randomtp.bukkit.teleport.WorldBorderRange;
import com.eternalcode.randomtp.bukkit.teleport.WorldHeightCorrector;
import com.eternalcode.randomtp.EternalRandomTp;
import com.eternalcode.randomtp.command.ProfileParameter;
import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.teleport.RandomTeleportAlgorithm;
import com.eternalcode.randomtp.teleport.TeleportService;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class PluginEternalRandomTp extends JavaPlugin {

    private EternalRandomTp eternalRandomTp;

    @Override
    public void onEnable() {
        Scheduler scheduler = new SchedulerBukkit(this);
        TeleportService teleportService = new TeleportService(scheduler, new RandomTeleportAlgorithm(), new WorldBorderRange(), new WorldHeightCorrector());

        this.eternalRandomTp = EternalRandomTp.builder()
                .dataFolder(this.getDataFolder())
                .scheduler(scheduler)
                .teleportService(teleportService)
                .liteCommandsBuilder(LiteBukkitFactory.builder(this.getServer(), "eternal-randomtp"))
                .profileExtractor(invocation -> BukkitConverter.convert(invocation.sender().getSender()))
                .build();
    }

    public EternalRandomTp getEternalRandomTp() {
        return eternalRandomTp;
    }

}
