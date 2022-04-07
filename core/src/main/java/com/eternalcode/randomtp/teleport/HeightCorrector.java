package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Position;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface HeightCorrector {

    HeightCorrector NONE = CompletableFuture::completedFuture;

    CompletableFuture<Position> correctPosition(Position position);

}
