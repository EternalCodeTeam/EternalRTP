package com.eternalcode.randomtp.command;

import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.teleport.game.TeleportGame;
import com.eternalcode.randomtp.teleport.game.TeleportGameRepository;
import com.eternalcode.randomtp.teleport.game.TeleportType;
import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.SingleArgumentHandler;
import dev.rollczi.litecommands.valid.ValidationCommandException;

import java.util.ArrayList;
import java.util.List;

@ArgumentName("teleport")
public class TeleportGameArg implements SingleArgumentHandler<TeleportGame> {

    private final TeleportGameRepository repository;
    private final CdnPluginConfig pluginConfig;

    public TeleportGameArg(TeleportGameRepository repository, CdnPluginConfig pluginConfig) {
        this.repository = repository;
        this.pluginConfig = pluginConfig;
    }

    @Override
    public TeleportGame parse(LiteInvocation invocation, String argument) throws ValidationCommandException {
        return this.repository.getTeleport(argument).orElseThrow(() -> new ValidationCommandException(pluginConfig.teleportTypeNotExist));
    }

    @Override
    public List<String> tabulation(LiteInvocation invocation, String command, String[] args) {
        return repository.getTeleports().stream().map(TeleportGame::getName).toList();
    }

}
