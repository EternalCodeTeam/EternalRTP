package com.eternalcode.bukkit.teleport;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeleportButtonController implements Listener {

    @EventHandler
    public void onButtonClick(PlayerInteractEvent event) {
        Action action = event.getAction();

        if (action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        // TODO: Check if player clicked on teleport button
    }


}
