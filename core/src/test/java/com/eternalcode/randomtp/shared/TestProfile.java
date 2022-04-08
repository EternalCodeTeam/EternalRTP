package com.eternalcode.randomtp.shared;

import com.eternalcode.randomtp.profile.Profile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
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
    public Universe getUniverse() {
        return Universe.NONE;
    }

    @Override
    public CompletableFuture<Boolean> teleport(Position position) {
        this.teleportCount++;
        return CompletableFuture.completedFuture(true);
    }

    public int getTeleports() {
        return this.teleportCount;
    }
}
