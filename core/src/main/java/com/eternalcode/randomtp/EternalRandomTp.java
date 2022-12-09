package com.eternalcode.randomtp;

import com.eternalcode.randomtp.command.ProfileContextual;
import com.eternalcode.randomtp.command.RandomTpCommand;
import com.eternalcode.randomtp.command.TeleportGameArg;
import com.eternalcode.randomtp.command.TeleportTypeArg;
import com.eternalcode.randomtp.config.CdnConfigManager;
import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.shared.Valid;
import com.eternalcode.randomtp.teleport.TeleportAlgorithm;
import com.eternalcode.randomtp.teleport.TeleportFilter;
import com.eternalcode.randomtp.teleport.game.TeleportGame;
import com.eternalcode.randomtp.teleport.game.TeleportGameController;
import com.eternalcode.randomtp.teleport.game.TeleportGameRepository;
import com.eternalcode.randomtp.teleport.TeleportCorrector;
import com.eternalcode.randomtp.teleport.TeleportRange;
import com.eternalcode.randomtp.teleport.TeleportService;
import com.eternalcode.randomtp.teleport.game.TeleportType;
import com.eternalcode.randomtp.teleport.game.TeleportTypeRegistry;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.LiteCommandsBuilder;

import java.io.File;

public class EternalRandomTp<SENDER> {

    private final Scheduler scheduler;
    private final Game game;

    private final TeleportAlgorithm algorithm;
    private final TeleportRange teleportRange;
    private final TeleportCorrector preCorrector;
    private final TeleportCorrector postCorrector;
    private final TeleportGameRepository teleportRepository;
    private final TeleportTypeRegistry typeRegistry;

    private final TeleportService teleportService;
    private final TeleportGameController teleportGameController;

    private final File dataFolder;
    private final CdnConfigManager configManager;

    private final LiteCommands<SENDER> liteCommands;

    private EternalRandomTp(
            Game game,
            Scheduler scheduler,

            File dataFolder,
            CdnConfigManager configManager,

            TeleportAlgorithm algorithm,
            TeleportRange teleportRange,
            TeleportCorrector preCorrector,
            TeleportCorrector postCorrector,

            TeleportGameRepository teleportRepository,
            TeleportTypeRegistry typeRegistry,

            LiteCommandsBuilder<SENDER> builder,
            ProfileContextual.Extractor<SENDER> profileExtractor
    ) {
        this.game = game;
        this.scheduler = scheduler;

        this.dataFolder = dataFolder;
        this.configManager = configManager;

        CdnPluginConfig config = this.configManager.getPluginConfig();

        this.algorithm = algorithm;
        this.teleportRange = teleportRange;
        this.preCorrector = preCorrector;
        this.postCorrector = postCorrector;
        this.teleportRepository = teleportRepository;
        this.typeRegistry = typeRegistry;

        this.teleportService = new TeleportService(this.algorithm, this.teleportRange, this.preCorrector, this.postCorrector, scheduler, game);
        this.teleportService.registerFilter(new TeleportFilter.BlackList(() -> config.blocked));
        this.teleportGameController = new TeleportGameController(this.teleportService, this.teleportRepository, this.typeRegistry, config, this.game);

        this.teleportService.setSettings(config);

        this.liteCommands = builder
                .command(RandomTpCommand.class)

                .typeBind(TeleportService.class, () -> this.teleportService)
                .typeBind(TeleportGameRepository.class, () -> this.teleportRepository)
                .typeBind(CdnPluginConfig.class, () -> config)
                .typeBind(Game.class, () -> this.game)

                .invalidUsageHandler((sender, invocation, schematic) -> invocation.sender().sendMessage(config.invalidUsage.replace("{command}", schematic.first())))
                .contextualBind(Profile.class, new ProfileContextual<>(profileExtractor))

                .argument(TeleportType.class, new TeleportTypeArg(this.typeRegistry, config))
                .argument(TeleportGame.class, new TeleportGameArg(this.teleportRepository, config))
                .register();
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public Game getGame() {
        return game;
    }

    public TeleportAlgorithm getAlgorithm() {
        return algorithm;
    }

    public TeleportRange getTeleportRange() {
        return teleportRange;
    }

    public TeleportCorrector getPreCorrector() {
        return preCorrector;
    }

    public TeleportGameRepository getTeleportRepository() {
        return teleportRepository;
    }

    public TeleportTypeRegistry getTypeRegistry() {
        return typeRegistry;
    }

    public TeleportService getTeleportService() {
        return teleportService;
    }

    public TeleportGameController getTeleportGameController() {
        return teleportGameController;
    }

    public File getDataFolder() {
        return dataFolder;
    }

    public CdnConfigManager getConfigManager() {
        return configManager;
    }

    public LiteCommands<SENDER> getLiteCommands() {
        return liteCommands;
    }

    public static <SENDER> Builder<SENDER> builder() {
        return new Builder<>();
    }

    public static class Builder<SENDER> {

        private Game game;
        private Scheduler scheduler;
        private File dataFolder;
        private CdnConfigManager cdnConfigManager;

        private TeleportAlgorithm algorithm;
        private TeleportRange teleportRange;
        private TeleportCorrector preCorrector;
        private TeleportCorrector postCorrector;
        private TeleportGameRepository teleportRepository;
        private TeleportTypeRegistry typeRegistry;

        private LiteCommandsBuilder<SENDER> builder;
        private ProfileContextual.Extractor<SENDER> profileExtractor;

        public Builder<SENDER> game(Game game) {
            this.game = game;
            return this;
        }

        public Builder<SENDER> scheduler(Scheduler scheduler) {
            this.scheduler = scheduler;
            return this;
        }

        public Builder<SENDER> dataFolder(File dataFolder) {
            this.dataFolder = dataFolder;
            return this;
        }

        public Builder<SENDER> cdnConfigManager(CdnConfigManager cdnConfigManager) {
            this.cdnConfigManager = cdnConfigManager;
            return this;
        }

        public Builder<SENDER> algorithm(TeleportAlgorithm algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public Builder<SENDER> teleportRange(TeleportRange teleportRange) {
            this.teleportRange = teleportRange;
            return this;
        }

        public Builder<SENDER> preCorrector(TeleportCorrector preCorrector) {
            this.preCorrector = preCorrector;
            return this;
        }

        public Builder<SENDER> postCorrector(TeleportCorrector postCorrector) {
            this.postCorrector = postCorrector;
            return this;
        }

        public Builder<SENDER> teleportRepository(TeleportGameRepository teleportRepository) {
            this.teleportRepository = teleportRepository;
            return this;
        }

        public Builder<SENDER> typeRegistry(TeleportTypeRegistry typeRegistry) {
            this.typeRegistry = typeRegistry;
            return this;
        }

        public Builder<SENDER> liteCommandsBuilder(LiteCommandsBuilder<SENDER> builder) {
            this.builder = builder;
            return this;
        }

        public Builder<SENDER> profileExtractor(ProfileContextual.Extractor<SENDER> profileExtractor) {
            this.profileExtractor = profileExtractor;
            return this;
        }

        public EternalRandomTp<SENDER> build() {
            Valid.notNull(game, "Game cannot be null");
            Valid.notNull(scheduler, "Scheduler cannot be null");
            Valid.notNull(dataFolder, "Data folder cannot be null");

            Valid.notNull(algorithm, "Algorithm cannot be null");
            Valid.notNull(teleportRange, "Teleport range cannot be null");
            Valid.notNull(preCorrector, "Position corrector cannot be null");

            Valid.notNull(builder, "LiteCommandsBuilder cannot be null");
            Valid.notNull(profileExtractor, "Profile extractor cannot be null");

            if (cdnConfigManager == null) {
                cdnConfigManager = new CdnConfigManager(scheduler, dataFolder);
            }

            cdnConfigManager.load();

            if (teleportRepository == null) {
                teleportRepository = cdnConfigManager.getTeleportData();
            }

            if (typeRegistry == null) {
                typeRegistry = cdnConfigManager.getPluginConfig();
            }

            return new EternalRandomTp<>(game, scheduler, dataFolder, cdnConfigManager, algorithm, teleportRange, preCorrector, postCorrector, teleportRepository, typeRegistry , builder, profileExtractor);
        }

    }

}
