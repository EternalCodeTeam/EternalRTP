package com.eternalcode.randomtp.bukkit.teleport;

import com.eternalcode.randomtp.bukkit.shared.BukkitProvider;
import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.BlockState;
import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.teleport.game.TeleportGameController;
import com.google.common.base.Stopwatch;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.concurrent.TimeUnit;

public class BukkitController implements Listener {

    private final TeleportGameController controller;

    public BukkitController(TeleportGameController controller) {
        this.controller = controller;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block block = event.getClickedBlock();

        if (block == null) {
            return;
        }

        Stopwatch started = Stopwatch.createStarted();
        Position position = BukkitProvider.convert(block.getLocation());
        BlockType blockType = BukkitProvider.convert(block.getType());
        Profile profile = BukkitProvider.convert(event.getPlayer());
        BlockState blockState = new BlockState(position, blockType);

        this.controller.handleRightClick(blockState, profile);
        double millisecond = started.elapsed(TimeUnit.NANOSECONDS) / 1000000.0;
        System.out.println("Right click took " + millisecond + " milliseconds");
    }

}
