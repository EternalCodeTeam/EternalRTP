package com.eternalcode.randomtp.config;

import com.eternalcode.randomtp.teleport.TeleportSettings;
import net.dzikoysk.cdn.entity.Description;

public class CdnPluginConfig implements TeleportSettings {

    @Description("# Próby znalezienia miejsca docelowego")
    public int attempts = 10;

    public String onTeleport = "&7Pomyślnie przeteleportowano do losowego miejsca.";
    public String onTeleportFail = "&cNie udało się bezpiecznej lokalizacji.";

    @Override
    public int getAttempts() {
        return this.attempts;
    }

}
