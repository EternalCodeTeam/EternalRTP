package com.eternalcode.randomtp.shared;

import java.util.Objects;

public final class BlockType {

    public final static BlockType NONE = BlockType.of("NONE");

    private final String name;

    private BlockType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNone() {
        return this.equals(NONE);
    }

    public static BlockType of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        return new BlockType(name.toUpperCase());
    }

    public static BlockType none() {
        return NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BlockType blockType)) {
            return false;
        }

        return this.name.equals(blockType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

}
