package com.eternalcode.randomtp.command;

import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.teleport.game.TeleportType;
import com.eternalcode.randomtp.teleport.game.TeleportTypeRegistry;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import panda.std.Result;

import java.util.List;

@ArgumentName("teleport_type")
public class TeleportTypeArg implements OneArgument<TeleportType> {

    private final TeleportTypeRegistry registry;
    private final CdnPluginConfig pluginConfig;

    public TeleportTypeArg(TeleportTypeRegistry registry, CdnPluginConfig pluginConfig) {
        this.registry = registry;
        this.pluginConfig = pluginConfig;
    }

    @Override
    public Result<TeleportType, ?> parse(LiteInvocation invocation, String argument) {
        return this.registry.getType(argument)
                .toResult(this.pluginConfig.teleportTypeNotExist);
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return this.registry.getTypes().keySet().stream()
                .map(Suggestion::of)
                .toList();
    }

}
