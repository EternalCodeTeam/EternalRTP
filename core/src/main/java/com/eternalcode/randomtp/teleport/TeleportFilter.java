package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;

import java.util.concurrent.CompletableFuture;


@FunctionalInterface
public interface TeleportFilter {

    CompletableFuture<Boolean> check(Position position);

}
