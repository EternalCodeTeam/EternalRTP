package com.eternalcode.randomtp.shared;

import com.eternalcode.randomtp.profile.Profile;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface Game {

    BlockType getBlockType(Position pos);

    BlockState BlockState(Position pos);

    CompletableFuture<BlockState> getAsyncBlockState(Position pos);

    void setBlockType(Position pos, BlockType type);

    Optional<Button> getButtonIfPresent(Position pos);

    Collection<Profile> getNearbyProfiles(Position center, double radius);

}
