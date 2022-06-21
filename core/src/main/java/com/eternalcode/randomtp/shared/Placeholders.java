package com.eternalcode.randomtp.shared;

import com.eternalcode.randomtp.teleport.game.TeleportGame;

public final class Placeholders {

    public static String format(String format, Position position) {
        return format
                .replace("{world}", position.getUniverse().getName())
                .replace("{x}", String.valueOf(position.getBlockX()))
                .replace("{y}", String.valueOf(position.getBlockY()))
                .replace("{z}", String.valueOf(position.getBlockZ()));
    }

    public static String format(String format, TeleportGame teleport) {
        return format(format, teleport.getCenter())
                .replace("{name}", String.valueOf(teleport.getName()))
                .replace("{type}", String.valueOf(teleport.getType()));
    }

}
