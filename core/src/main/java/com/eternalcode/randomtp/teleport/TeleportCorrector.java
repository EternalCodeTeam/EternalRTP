package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Position;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface TeleportCorrector {

    TeleportCorrector NONE = CompletableFuture::completedFuture;

    CompletableFuture<Position> correct(Position position);

}
