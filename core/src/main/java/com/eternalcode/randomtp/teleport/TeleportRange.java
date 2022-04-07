package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Universe;


@FunctionalInterface
public interface TeleportRange {

    Box getRange(Universe universe);

    static TeleportRange of(Box box) {
        return position -> box;
    }

}
