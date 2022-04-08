package com.eternalcode.randomtp.teleport.game;

import com.eternalcode.randomtp.shared.Position;

import java.util.Collection;
import java.util.Optional;

public interface TeleportGameRepository {

    Collection<TeleportGame> getTeleports();

    Optional<TeleportGame> getTeleport(String name);

    void saveTeleport(String name, Position position, String type);

    void deleteTeleport(String name);

}
