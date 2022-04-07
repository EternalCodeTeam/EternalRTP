package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;


@FunctionalInterface
public interface TeleportFilter {

    boolean isValid(Universe universe, Position position);

}
