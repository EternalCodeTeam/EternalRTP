package com.eternalcode.randomtp;

import com.eternalcode.randomtp.command.ProfileParameter;
import com.eternalcode.randomtp.command.RandomTpCommand;
import com.eternalcode.randomtp.config.CdnConfigManager;
import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.teleport.TeleportService;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.LiteCommandsBuilder;
import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.bind.Parameter;
import dev.rollczi.litecommands.platform.LitePlatformManager;

import java.io.File;

public class EternalRandomTp {

    private final Scheduler scheduler;
    private final TeleportService teleportService;

    private final File dataFolder;
    private final CdnConfigManager configManager;

    private final LiteCommands liteCommands;

    private  <S, P extends LitePlatformManager<S>> EternalRandomTp(
            File dataFolder,
            Scheduler scheduler,
            TeleportService teleportService,
            LiteCommandsBuilder<S, P> builder,
            ProfileParameter.Extractor profileExtractor
    ) {
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
                .parameterBind(Profile.class, new ProfileParameter(profileExtractor))
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private File dataFolder;
        private Scheduler scheduler;
        private TeleportService teleportService;
        private LiteCommandsBuilder<?, ?> liteCommandsBuilder;
        private ProfileParameter.Extractor profileExtractor;

        public Builder dataFolder(File dataFolder) {
            this.dataFolder = dataFolder;
            return this;
        }

        public Builder scheduler(Scheduler scheduler) {
            this.scheduler = scheduler;
            return this;
        }

        public Builder teleportService(TeleportService teleportService) {
            this.teleportService = teleportService;
            return this;
        }

        public Builder liteCommandsBuilder(LiteCommandsBuilder<?, ?> liteCommandsBuilder) {
            this.liteCommandsBuilder = liteCommandsBuilder;
            return this;
        }

        public Builder profileExtractor(ProfileParameter.Extractor profileExtractor) {
            this.profileExtractor = profileExtractor;
            return this;
        }

        public EternalRandomTp build() {
            if (dataFolder == null || scheduler == null || teleportService == null || liteCommandsBuilder == null || profileExtractor == null) {
                throw new IllegalStateException("Builder is not fully configured");
            }

            return new EternalRandomTp(dataFolder, scheduler, teleportService, liteCommandsBuilder, profileExtractor);
        }

    }

}
