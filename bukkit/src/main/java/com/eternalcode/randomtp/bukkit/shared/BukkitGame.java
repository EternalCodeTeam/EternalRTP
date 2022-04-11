package com.eternalcode.randomtp.bukkit.shared;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.BlockState;
import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Button;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Position;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.block.data.type.Switch;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class BukkitGame implements Game {

    @Override
    public BlockType getBlockType(Position pos) {
        Location location = BukkitProvider.convert(pos);
        Material type = location.getBlock().getType();

        return BukkitProvider.convert(type);
    }

    @Override
    public void setBlockType(Position pos, BlockType type) {
        Location location = BukkitProvider.convert(pos);
        location.getBlock().setType(BukkitProvider.convert(type));
    }

    @Override
    public Optional<Button> getButtonIfPresent(Position pos) {
        Block block = BukkitProvider.convert(pos).getBlock();

        return block.getBlockData() instanceof Switch
                ? Optional.of(new BukkitButton(block))
                : Optional.empty();
    }

    @Override
    public Collection<Profile> getNearbyProfiles(Position center, double radius) {
        Location location = BukkitProvider.convert(center);
        World world = location.getWorld();

        if (world == null) {
            return Collections.emptyList();
        }

        Set<Profile> profiles = new HashSet<>();

        for (Entity entity : world.getNearbyEntities(location, radius, radius, radius)) {
            if (!(entity instanceof Player player)) {
                continue;
            }

            profiles.add(BukkitProvider.convert(player));
        }

        return profiles;
    }

    private static class BukkitButton implements Button {

        private final Block block;

        private BukkitButton(Block block) {
            if (!(block.getBlockData() instanceof Switch)) {
                throw new IllegalStateException("Block is not a button");
            }

            this.block = block;
        }

        @Override
        public boolean isPowered() {
            return this.block.getBlockData() instanceof Switch switchData && switchData.isPowered();
        }

        @Override
        public void setPowered(boolean powered) {
            if (!(this.block.getBlockData() instanceof Switch switchData)) {
                throw new IllegalStateException("Block is not a button");
            }

            switchData.setPowered(powered);
            this.block.setBlockData(switchData);
        }

        @Override
        public BlockState getPillar() {
            if (!(this.block.getBlockData() instanceof Switch switchData)) {
                throw new IllegalStateException("Block is not a button");
            }

            if (switchData.getAttachedFace() == FaceAttachable.AttachedFace.FLOOR) {
                return BukkitProvider.convert(block.getRelative(BlockFace.DOWN));
            }

            if (switchData.getAttachedFace() == FaceAttachable.AttachedFace.CEILING) {
                return BukkitProvider.convert(block.getRelative(BlockFace.UP));
            }

            return BukkitProvider.convert(block.getRelative(switchData.getFacing().getOppositeFace()));
        }

        @Override
        public void setPillar(Position pillar) {
            Location location = BukkitProvider.convert(pillar);
            Block block = location.getBlock();
            BlockFace face = this.block.getFace(block);

            if (face == null) {
                return;
            }

            if (!(this.block.getBlockData() instanceof Switch switchData)) {
                throw new IllegalStateException("Block is not a button");
            }

            switchData.setFacing(face.getOppositeFace());
            this.block.setBlockData(switchData);
        }

    }
}
