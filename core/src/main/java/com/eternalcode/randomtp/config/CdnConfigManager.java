package com.eternalcode.randomtp.config;

import net.dzikoysk.cdn.Cdn;
import net.dzikoysk.cdn.CdnFactory;
import net.dzikoysk.cdn.source.Resource;
import net.dzikoysk.cdn.source.Source;

import java.io.File;

public class CdnConfigManager {

    private final Cdn cdn = CdnFactory.createYamlLike();
    private final File dataFolder;

    private final CdnPluginConfig pluginConfig = new CdnPluginConfig();

    public CdnConfigManager(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    public void load() {
        this.loadAndRender("config.yml", this.pluginConfig);
    }

    public CdnPluginConfig getPluginConfig() {
        return pluginConfig;
    }

    private void loadAndRender(String name, Object object) {
        Resource resource = Source.of(this.dataFolder, name);

        this.cdn.load(resource, object).orElseThrow(ex -> new RuntimeException("Failed to load config " + name, ex));
        this.cdn.render(object, resource).orElseThrow(ex -> new RuntimeException("Failed to render config " + name, ex));
    }

}
