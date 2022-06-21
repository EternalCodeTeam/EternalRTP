package com.eternalcode.randomtp.test;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.BlockState;
import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Button;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Position;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class GameTest implements Game {

    @Override
    public BlockType getBlockType(Position pos) {
        return BlockType.NONE;
    }

    @Override
    public BlockState BlockState(Position pos) {
        return new BlockState(pos, BlockType.NONE);
    }

    @Override
    public CompletableFuture<BlockState> getAsyncBlockState(Position pos) {
        return CompletableFuture.completedFuture(new BlockState(pos, BlockType.NONE));
    }

    @Override
    public void setBlockType(Position pos, BlockType type) {
    }

    @Override
    public Optional<Button> getButtonIfPresent(Position pos) {
        return Optional.empty();
    }

    @Override
    public Collection<Profile> getNearbyProfiles(Position center, double radius) {
        return Collections.emptyList();
    }

}
