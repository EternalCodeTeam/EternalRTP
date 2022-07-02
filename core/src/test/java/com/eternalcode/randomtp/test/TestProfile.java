package com.eternalcode.randomtp.test;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProfile implements Profile {

    private final UUID uuid;
    private int teleportCount;
    private final List<String> messages = new ArrayList<>();

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
        this.messages.add(message);
    }

    public void assertTeleportCount(int expected) {
        assertEquals(expected, this.teleportCount);
    }

    public void assertMessages(String... expected) {
        assertEquals(expected.length, this.messages.size());

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], this.messages.get(i));
        }
    }

}
