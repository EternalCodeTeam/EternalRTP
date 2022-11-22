package com.eternalcode.randomtp.teleport.game;

import panda.std.Option;

import java.util.Map;
import java.util.Optional;

public interface TeleportTypeRegistry {

    Map<String, TeleportType> getTypes();

    Option<TeleportType> getType(String name);

}
