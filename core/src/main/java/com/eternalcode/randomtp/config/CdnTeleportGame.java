package com.eternalcode.randomtp.config;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import com.eternalcode.randomtp.teleport.game.TeleportGame;
import net.dzikoysk.cdn.entity.Contextual;

@Contextual
public class CdnTeleportGame implements TeleportGame {

    public String name = "default";
    public Position center = new Position(Universe.of("world"), 0, 0, 0);
    public String type = "normal";

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Position getCenter() {
        return this.center;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public CdnTeleportGame() {
    }

    public CdnTeleportGame(String name, Position center, String type) {
        this.name = name;
        this.center = center;
        this.type = type;
    }

}
