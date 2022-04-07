package com.eternalcode.randomtp.command;

import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.teleport.TeleportService;
import dev.rollczi.litecommands.annotations.Execute;
import dev.rollczi.litecommands.annotations.Permission;
import dev.rollczi.litecommands.annotations.Section;
import dev.rollczi.litecommands.platform.LiteSender;

@Section(route = "randomtp", aliases = { "rtp" })
@Permission("eternalcode.command.randomtp")
public class RandomTpCommand {

    private final TeleportService teleportService;
    private final CdnPluginConfig pluginConfig;

    public RandomTpCommand(TeleportService teleportService, CdnPluginConfig pluginConfig) {
        this.teleportService = teleportService;
        this.pluginConfig = pluginConfig;
    }

    @Execute
    public void execute(LiteSender sender, Profile profile) {
        this.teleportService.teleportProfile(profile, profile.getUniverse(), result -> sender.sendMessage(result.isSuccess() ? pluginConfig.onTeleport : pluginConfig.onTeleportFail));
    }

}
