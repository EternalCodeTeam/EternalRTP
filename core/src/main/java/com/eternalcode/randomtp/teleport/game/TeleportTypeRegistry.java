package com.eternalcode.randomtp.teleport.game;

import java.util.Map;
import java.util.Optional;

public interface TeleportTypeRegistry {

    Map<String, TeleportType> getTypes();

    Optional<TeleportType> getType(String name);

}
