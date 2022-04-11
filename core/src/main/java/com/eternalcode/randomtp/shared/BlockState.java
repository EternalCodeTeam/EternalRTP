package com.eternalcode.randomtp.shared;

import java.util.Objects;

public class BlockState {

    private final Position position;
    private final BlockType blockType;

    public BlockState(Position position, BlockType blockType) {
        this.position = position;
        this.blockType = blockType;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BlockState that)) {
            return false;
        }

        return position.equals(that.position) && blockType.equals(that.blockType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, blockType);
    }
}
