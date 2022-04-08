package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Position;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface TeleportPositionCorrector {

    TeleportPositionCorrector NONE = CompletableFuture::completedFuture;

    CompletableFuture<Position> correctPosition(Position position);

}
