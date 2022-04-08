package com.eternalcode.randomtp.command;

import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.teleport.game.TeleportType;
import com.eternalcode.randomtp.teleport.game.TeleportTypeRegistry;
import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.SingleArgumentHandler;
import dev.rollczi.litecommands.valid.ValidationCommandException;

import java.util.ArrayList;
import java.util.List;

@ArgumentName("telepor_ttype")
public class TeleportTypeArg implements SingleArgumentHandler<TeleportType> {

    private final TeleportTypeRegistry registry;
    private final CdnPluginConfig pluginConfig;

    public TeleportTypeArg(TeleportTypeRegistry registry, CdnPluginConfig pluginConfig) {
        this.registry = registry;
        this.pluginConfig = pluginConfig;
    }

    @Override
    public TeleportType parse(LiteInvocation invocation, String argument) throws ValidationCommandException {
        return this.registry.getType(argument).orElseThrow(() -> new ValidationCommandException(pluginConfig.teleportTypeNotExist));
    }

    @Override
    public List<String> tabulation(LiteInvocation invocation, String command, String[] args) {
        return new ArrayList<>(registry.getTypes().keySet());
    }

}
