package com.eternalcode.randomtp.bukkit;

import com.eternalcode.randomtp.EternalRandomTp;
import com.eternalcode.randomtp.bukkit.shared.BukkitProvider;
import com.eternalcode.randomtp.bukkit.teleport.BukkitController;
import com.eternalcode.randomtp.bukkit.teleport.WorldBorderRange;
import com.eternalcode.randomtp.bukkit.teleport.WorldTeleportPositionCorrector;
import com.eternalcode.randomtp.teleport.RandomTeleportAlgorithm;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginEternalRandomTp extends JavaPlugin {

    private EternalRandomTp eternalRandomTp;

    @Override
    public void onEnable() {
        this.eternalRandomTp = EternalRandomTp.builder()
                .game(BukkitProvider.createGame())
                .scheduler(BukkitProvider.createScheduler(this))
                .dataFolder(this.getDataFolder())

                .teleportRange(new WorldBorderRange())
                .corrector(new WorldTeleportPositionCorrector())
                .algorithm(new RandomTeleportAlgorithm())

                .liteCommandsBuilder(LiteBukkitFactory.builder(this.getServer(), "eternal-randomtp"))
                .profileExtractor(invocation -> BukkitProvider.convert(invocation.sender().getSender()))
                .build();

        this.getServer().getPluginManager().registerEvents(new BukkitController(this.eternalRandomTp.getTeleportGameController()), this);
    }

    public EternalRandomTp getEternalRandomTp() {
        return eternalRandomTp;
    }

}
