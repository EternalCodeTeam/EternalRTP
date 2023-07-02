package com.eternalcode.randomteleport.config;

import com.eternalcode.randomteleport.config.composer.PositionComposer;
import com.eternalcode.randomteleport.shared.Position;
import net.dzikoysk.cdn.Cdn;
import net.dzikoysk.cdn.CdnFactory;
import net.dzikoysk.cdn.reflect.Visibility;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ConfigManager {

    private final Cdn cdn = CdnFactory
            .createYamlLike()
            .getSettings()
            .withMemberResolver(Visibility.PRIVATE)
            .withMemberResolver(Visibility.PACKAGE_PRIVATE)
            .withComposer(Position.class, new PositionComposer())
            .build();

    private final Set<ReloadableConfig> configs = new HashSet<>();
    private final File dataFolder;

    public ConfigManager(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    public <T extends ReloadableConfig> T load(T config) {
        this.cdn.load(config.resource(this.dataFolder), config)
                .orThrow(RuntimeException::new);

        this.cdn.render(config, config.resource(this.dataFolder))
                .orThrow(RuntimeException::new);

        this.configs.add(config);

        return config;
    }

    public <T extends ReloadableConfig> void save(T config) {
        this.cdn.render(config, config.resource(this.dataFolder))
                .orThrow(RuntimeException::new);
    }

    public void reload() {
        for (ReloadableConfig config : this.configs) {
            this.load(config);
        }
    }

}