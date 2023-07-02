package com.eternalcode.randomteleport.config.impl;

import com.eternalcode.randomteleport.config.ReloadableConfig;
import com.eternalcode.randomteleport.teleport.TeleportButtonPlace;
import net.dzikoysk.cdn.entity.Description;
import net.dzikoysk.cdn.source.Resource;
import net.dzikoysk.cdn.source.Source;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ButtonDataConfig implements ReloadableConfig {

    @Description("# Please don't touch this, this is automatically added by /rtp setbutton command")
    public List<TeleportButtonPlace> buttons = new ArrayList<>();

    @Override
    public Resource resource(File folder) {
        return Source.of(folder, "buttons.yml");
    }

}
