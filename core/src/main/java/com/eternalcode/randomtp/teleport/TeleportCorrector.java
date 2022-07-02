package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Position;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface TeleportCorrector {

    CompletableFuture<Position> correct(Position position);

    TeleportCorrector NONE = CompletableFuture::completedFuture;
    TeleportCorrector FRIENDLY_POSITION = position -> CompletableFuture.completedFuture(position.add(0.5, 1, 0.5));

}
