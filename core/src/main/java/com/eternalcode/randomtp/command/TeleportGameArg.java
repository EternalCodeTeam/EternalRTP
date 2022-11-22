package com.eternalcode.randomtp.command;

import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.teleport.game.TeleportGame;
import com.eternalcode.randomtp.teleport.game.TeleportGameRepository;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import panda.std.Result;

import java.util.List;

@ArgumentName("teleport")
public class TeleportGameArg implements OneArgument<TeleportGame> {

    private final TeleportGameRepository repository;
    private final CdnPluginConfig pluginConfig;

    public TeleportGameArg(TeleportGameRepository repository, CdnPluginConfig pluginConfig) {
        this.repository = repository;
        this.pluginConfig = pluginConfig;
    }

    @Override
    public Result<TeleportGame, ?> parse(LiteInvocation invocation, String argument) {
        return this.repository.getTeleport(argument)
                .toResult(pluginConfig.teleportTypeNotExist);
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return repository.getTeleports().stream()
                .map(TeleportGame::getName)
                .map(Suggestion::of)
                .toList();
    }

}
