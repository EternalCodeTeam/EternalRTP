package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.BlockState;
import com.eternalcode.randomtp.shared.BlockType;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.logging.Logger;


@FunctionalInterface
public interface TeleportFilter {

    boolean check(BlockState state);

    class BlackList implements TeleportFilter {

        private final Supplier<Collection<BlockType>> referenceBlocked;

        public BlackList(Supplier<Collection<BlockType>> referenceBlocked) {
            this.referenceBlocked = referenceBlocked;
        }

        @Override
        public boolean check(BlockState state) {
            for (BlockType blockType : this.referenceBlocked.get()) {
                if (state.getBlockType().equals(blockType)) {
                    return false;
                }
            }

            return true;
        }

    }

}
