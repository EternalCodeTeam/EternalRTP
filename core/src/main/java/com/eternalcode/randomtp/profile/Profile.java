package com.eternalcode.randomtp.profile;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface Profile {

    UUID getUuid();

    Position getPosition();

    Optional<Position> getTargetPosition();

    Universe getUniverse();

    CompletableFuture<Boolean> teleport(Position position);

}
