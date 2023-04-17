package com.eternalcode.randomteleport.config.impl;

import com.eternalcode.randomteleport.config.ReloadableConfig;
import com.eternalcode.randomteleport.teleport.TeleportButtonPlace;
import net.dzikoysk.cdn.source.Resource;
import net.dzikoysk.cdn.source.Source;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ButtonDataConfig implements ReloadableConfig {

    public Map<String, TeleportButtonPlace> buttons = new HashMap<>();

    @Override
    public Resource resource(File folder) {
        return Source.of(folder, "buttons.yml");
    }
}