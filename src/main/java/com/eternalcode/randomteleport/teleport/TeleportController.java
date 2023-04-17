package com.eternalcode.randomteleport.teleport;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeleportController implements Listener {

    @EventHandler
    void interact(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Hello world", event.getClickedBlock().getLocation().toString());
    }
}
