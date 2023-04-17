package com.eternalcode.randomteleport.teleport;

import com.eternalcode.randomteleport.shared.Position;
import net.dzikoysk.cdn.entity.Contextual;

@Contextual
public final class TeleportButtonPlace {

    public String name;
    public Position position;
    public TeleportButtonType type;

    public TeleportButtonPlace(String name, Position position, TeleportButtonType type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

}
