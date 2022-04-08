package com.eternalcode.randomtp.config;

import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import net.dzikoysk.cdn.Cdn;
import net.dzikoysk.cdn.CdnFactory;
import net.dzikoysk.cdn.source.Resource;
import net.dzikoysk.cdn.source.Source;
import panda.std.Result;

import java.io.File;

public class CdnConfigManager {

    private final Cdn cdn = CdnFactory.createYamlLike().getSettings()
            .withComposer(BlockType.class, blockType -> Result.ok(blockType.getName()), name -> Result.ok(BlockType.of(name)))
            .withComposer(Universe.class, universe -> Result.ok(universe.getName()), name -> Result.ok(Universe.of(name)))
            .withComposer(Position.class, new PositionComposer())
            .build();

    private final File dataFolder;
    private final CdnPluginConfig pluginConfig = new CdnPluginConfig();
    private final CdnTeleportGameRepository teleportData = new CdnTeleportGameRepository();

    public CdnConfigManager(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    public void load() {
        this.loadAndRender("config.yml", this.pluginConfig);
        this.loadAndRender("data.yml", this.teleportData);
    }

    public CdnPluginConfig getPluginConfig() {
        return pluginConfig;
    }

    public CdnTeleportGameRepository getTeleportData() {
        return teleportData;
    }

    private void loadAndRender(String name, Object object) {
        Resource resource = Source.of(this.dataFolder, name);

        this.cdn.load(resource, object).orElseThrow(ex -> new RuntimeException("Failed to load config " + name, ex));
        this.cdn.render(object, resource).orElseThrow(ex -> new RuntimeException("Failed to render config " + name, ex));
    }

}
