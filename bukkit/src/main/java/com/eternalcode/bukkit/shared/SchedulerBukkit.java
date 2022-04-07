package com.eternalcode.bukkit.shared;

import com.eternalcode.randomtp.shared.Scheduler;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class SchedulerBukkit implements Scheduler {

    private final Plugin plugin;
    private final BukkitScheduler rootScheduler;

    public SchedulerBukkit(Plugin plugin) {
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
