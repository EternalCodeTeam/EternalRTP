package com.eternalcode.randomtp.test;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
    public Position getPosition() {
        return Position.ZERO;
    }

    @Override
    public Optional<Position> getTargetPosition() {
        return Optional.empty();
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

    @Override
    public void sendMessage(String message) {

    }

    public int getTeleports() {
        return this.teleportCount;
    }

}
