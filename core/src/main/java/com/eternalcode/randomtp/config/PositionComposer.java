package com.eternalcode.randomtp.config;

import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;
import net.dzikoysk.cdn.serdes.Composer;
import net.dzikoysk.cdn.serdes.SimpleDeserializer;
import net.dzikoysk.cdn.serdes.SimpleSerializer;
import panda.std.Result;

import java.util.Locale;

class PositionComposer implements Composer<Position>, SimpleSerializer<Position>, SimpleDeserializer<Position> {

    // universe:x:y:z:yaw:pitch

    @Override
    public Result<Position, Exception> deserialize(String source) {
        String[] split = source.split(":");

        if (split.length != 6) {
            return Result.error(new Exception("Invalid position format"));
        }

        return Result.ok(new Position(
                Universe.of(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5])
        ));
    }

    @Override
    public Result<String, Exception> serialize(Position entity) {
        return Result.ok(String.format(Locale.US, "%s:%f:%f:%f:%f:%f", entity.getUniverse().getName(), entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch()));
    }

}
