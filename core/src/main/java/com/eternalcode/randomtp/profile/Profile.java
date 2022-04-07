package com.eternalcode.randomtp.profile;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;

import java.util.UUID;

public interface Profile {

    UUID getUuid();

    Universe getUniverse();

    void teleport(Position position);

}
