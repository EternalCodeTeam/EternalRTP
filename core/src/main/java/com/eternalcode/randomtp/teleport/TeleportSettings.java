package com.eternalcode.randomtp.teleport;

public interface TeleportSettings {

    int getAttempts();

    TeleportSettings DEFAULT = () -> 10;

}
