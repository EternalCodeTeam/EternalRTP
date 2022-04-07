package com.eternalcode.bukkit;

import com.eternalcode.bukkit.shared.SchedulerBukkit;
import com.eternalcode.bukkit.teleport.WorldBorderRange;
import com.eternalcode.bukkit.teleport.WorldHeightCorrector;
import com.eternalcode.randomtp.EternalRandomTp;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.teleport.RandomTeleportAlgorithm;
import com.eternalcode.randomtp.teleport.TeleportService;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginEternalRandomTp extends JavaPlugin {

    private EternalRandomTp eternalRandomTp;

    @Override
    public void onEnable() {
        Scheduler scheduler = new SchedulerBukkit(this);
        TeleportService teleportService = new TeleportService(
                scheduler,
                new RandomTeleportAlgorithm(),
                new WorldBorderRange(),
                new WorldHeightCorrector()
        );

        this.eternalRandomTp = new EternalRandomTp(
                this.getDataFolder(),
                scheduler,
                teleportService,
                LiteBukkitFactory.builder(this.getServer(), "eternal-randomtp")
        );
    }

    public EternalRandomTp getEternalRandomTp() {
        return eternalRandomTp;
    }

}
