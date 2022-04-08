package com.eternalcode.randomtp.bukkit.shared;

import com.eternalcode.randomtp.shared.Scheduler;
import org.bukkit.plugin.Plugin;

class BukkitScheduler implements Scheduler {

    private final Plugin plugin;
    private final org.bukkit.scheduler.BukkitScheduler rootScheduler;

    public BukkitScheduler(Plugin plugin) {
        this.plugin = plugin;
        this.rootScheduler = this.plugin.getServer().getScheduler();
    }

    @Override
    public void runSync(Runnable runnable) {
        this.rootScheduler.runTask(this.plugin, runnable);
    }

    @Override
    public void runAsync(Runnable runnable) {
        this.rootScheduler.runTaskAsynchronously(this.plugin, runnable);
    }

}
