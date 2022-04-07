package com.eternalcode.randomtp.shared;

import com.eternalcode.randomtp.profile.Profile;

import java.util.UUID;
import java.util.function.Consumer;

public class TestProfile implements Profile {

    private final UUID uuid;
    private int teleportCount;

    public TestProfile() {
        this.uuid = UUID.randomUUID();
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public void teleport(Position position) {
        this.teleportCount++;
    }

    public int getTeleports() {
        return this.teleportCount;
    }
}
