package com.eternalcode.randomtp.test;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.BlockState;
import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Button;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Position;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class GameTest implements Game {

    private final Map<Position, BlockType> blockTypes = new HashMap<>();
    private final Map<Position, BlockState> buttons = new HashMap<>();

    @Override
    public BlockType getBlockType(Position pos) {
        return this.blockTypes.getOrDefault(pos, BlockType.NONE);
    }

    @Override
    public BlockState BlockState(Position pos) {
        return new BlockState(pos, this.getBlockType(pos));
    }

    @Override
    public CompletableFuture<BlockState> getAsyncBlockState(Position pos) {
        return CompletableFuture.completedFuture(new BlockState(pos, BlockType.NONE));
    }

    @Override
    public void setBlockType(Position pos, BlockType type) {
        this.blockTypes.put(pos, type);
    }

    @Override
    public Optional<Button> getButtonIfPresent(Position pos) {
        return this.buttons.containsKey(pos) ? Optional.of(new TestButton(pos, this.buttons.get(pos))) : Optional.empty();
    }

    @Override
    public Collection<Profile> getNearbyProfiles(Position center, double radius) {
        return Collections.emptyList();
    }

    public void setButton(BlockState state, BlockState pillar) {
        this.buttons.put(state.getPosition(), pillar);
    }

    private static class TestButton implements Button {

        private final Position position;
        private final BlockState pillar;
        private boolean powered = false;

        private TestButton(Position position, BlockState pillar) {
            this.position = position;
            this.pillar = pillar;
        }

        @Override
        public boolean isPowered() {
            return this.powered;
        }

        @Override
        public void setPowered(boolean powered) {
            this.powered = powered;
        }

        @Override
        public BlockState getPillar() {
            return this.pillar;
        }

        @Override
        public void setPillar(Position pillar) {
        }

    }

}
