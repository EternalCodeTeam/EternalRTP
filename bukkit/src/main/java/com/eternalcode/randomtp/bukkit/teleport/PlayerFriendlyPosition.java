package com.eternalcode.randomtp.bukkit.teleport;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.teleport.TeleportCorrector;

import java.util.concurrent.CompletableFuture;

public class PlayerFriendlyPosition implements TeleportCorrector {
    
    @Override
    public CompletableFuture<Position> correct(Position position) {
        return CompletableFuture.completedFuture(position.add(0.5, 1, 0.5));
    }
    
}
