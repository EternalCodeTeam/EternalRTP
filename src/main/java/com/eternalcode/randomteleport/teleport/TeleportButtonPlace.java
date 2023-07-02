package com.eternalcode.randomteleport.teleport;

import com.eternalcode.randomteleport.shared.Position;
import net.dzikoysk.cdn.entity.Contextual;

@Contextual
public class TeleportButtonPlace {

    public String name;
    public Position position;
    public TeleportButtonType type = TeleportButtonType.SOLO;

    public TeleportButtonPlace(String name, Position position, TeleportButtonType type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    public TeleportButtonPlace() {}

    public String name() {
        return this.name;
    }

    public Position position() {
        return this.position;
    }

    public TeleportButtonType type() {
        return this.type;
    }

}
