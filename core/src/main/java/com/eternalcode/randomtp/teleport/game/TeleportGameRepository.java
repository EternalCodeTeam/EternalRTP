package com.eternalcode.randomtp.teleport.game;

import com.eternalcode.randomtp.shared.Position;
import panda.std.Option;

import java.util.Collection;

public interface TeleportGameRepository {

    Collection<TeleportGame> getTeleports();

    Option<TeleportGame> getTeleport(String name);

    void saveTeleport(String name, Position position, String type);

    void deleteTeleport(String name);

}
