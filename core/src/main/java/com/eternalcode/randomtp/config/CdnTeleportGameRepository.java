package com.eternalcode.randomtp.config;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.teleport.game.TeleportGame;
import com.eternalcode.randomtp.teleport.game.TeleportGameRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CdnTeleportGameRepository implements TeleportGameRepository {

    private final Runnable update;

    public Map<String, CdnTeleportGame> teleports = Map.of("default", new CdnTeleportGame());

    public CdnTeleportGameRepository(Runnable update) {
        this.update = update;
    }

    @Override
    public Collection<TeleportGame> getTeleports() {
        return Collections.unmodifiableCollection(teleports.values());
    }

    @Override
    public Optional<TeleportGame> getTeleport(String name) {
        return Optional.empty();
    }

    @Override
    public void saveTeleport(String name, Position position, String type) {
        Map<String, CdnTeleportGame> copy = new HashMap<>(teleports);
        copy.put(name, new CdnTeleportGame(name, position, type));

        this.teleports = Collections.unmodifiableMap(copy);
        this.update.run();
    }

    @Override
    public void deleteTeleport(String name) {
        Map<String, CdnTeleportGame> copy = new HashMap<>(teleports);
        copy.remove(name);

        this.teleports = Collections.unmodifiableMap(copy);
        this.update.run();
    }


}
