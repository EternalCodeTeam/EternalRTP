package com.eternalcode.randomtp.shared;

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

}
