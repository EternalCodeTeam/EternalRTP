package com.eternalcode.randomtp;

import com.eternalcode.randomtp.command.RandomTpCommand;
import com.eternalcode.randomtp.config.CdnConfigManager;
import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.teleport.TeleportService;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.LiteCommandsBuilder;
import dev.rollczi.litecommands.platform.LitePlatformManager;

import java.io.File;

public class EternalRandomTp {

    private final Scheduler scheduler;
    private final TeleportService teleportService;

    private final File dataFolder;
    private final CdnConfigManager configManager;

    private final LiteCommands liteCommands;

    public <S, P extends LitePlatformManager<S>> EternalRandomTp(File dataFolder, Scheduler scheduler, TeleportService teleportService, LiteCommandsBuilder<S, P> builder) {
        this.scheduler = scheduler;
        this.teleportService = teleportService;

        this.dataFolder = dataFolder;
        this.configManager = new CdnConfigManager(dataFolder);
        this.configManager.load();
        this.teleportService.setSettings(this.configManager.getPluginConfig());

        this.liteCommands = builder
                .command(RandomTpCommand.class)
                .typeBind(TeleportService.class, () -> this.teleportService)
                .typeBind(CdnPluginConfig.class, this.configManager::getPluginConfig)
                .register();
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public TeleportService getTeleportService() {
        return teleportService;
    }

    public File getDataFolder() {
        return dataFolder;
    }

    public CdnConfigManager getConfigManager() {
        return configManager;
    }

    public LiteCommands getLiteCommands() {
        return liteCommands;
    }

}
