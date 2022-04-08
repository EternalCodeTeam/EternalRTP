package com.eternalcode.randomtp.config;

import com.eternalcode.randomtp.teleport.game.TeleportTypeRegistry;
import com.eternalcode.randomtp.teleport.TeleportSettings;
import com.eternalcode.randomtp.teleport.game.TeleportType;
import net.dzikoysk.cdn.entity.Description;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class CdnPluginConfig implements TeleportSettings, TeleportTypeRegistry {

    @Description("# Typy teleportów")
    public Map<String, CdnTeleportType> types = Map.of(
        "normal", new CdnTeleportType()
    );

    @Description("# Próby znalezienia miejsca docelowego")
    public int attempts = 10;

    public String onTeleport = "&7Pomyślnie przeteleportowano do losowego miejsca.";
    public String onTeleportFail = "&cNie udało się bezpiecznej lokalizacji.";
    public String invalidUsage = "&cNieprawidłowe użycie komendy! &7{command}";
    public String teleportTypeNotExist = "&cNie ma takiego typu teleportu!";
    public String onNoPosition = "&cNie patrzysz na blok!";
    public String onCreate = "&aUtworzono nowy teleport!";
    public String onDelete = "&aUsunięto teleport!";

    @Override
    public int getAttempts() {
        return this.attempts;
    }

    @Override
    public Map<String, TeleportType> getTypes() {
        return Collections.unmodifiableMap(this.types);
    }

    @Override
    public Optional<TeleportType> getType(String name) {
        return Optional.ofNullable(this.types.get(name));
    }

}
