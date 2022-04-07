package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@FunctionalInterface
public interface TeleportAlgorithm {

    Position createPosition(Box box, Universe universe);

}
