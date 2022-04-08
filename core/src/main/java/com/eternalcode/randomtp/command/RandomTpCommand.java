package com.eternalcode.randomtp.command;

import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.teleport.TeleportService;
import com.eternalcode.randomtp.teleport.game.TeleportGameRepository;
import com.eternalcode.randomtp.teleport.game.TeleportType;
import dev.rollczi.litecommands.annotations.Arg;
import dev.rollczi.litecommands.annotations.Execute;
import dev.rollczi.litecommands.annotations.IgnoreMethod;
import dev.rollczi.litecommands.annotations.Name;
import dev.rollczi.litecommands.annotations.Permission;
import dev.rollczi.litecommands.annotations.Section;
import dev.rollczi.litecommands.platform.LiteSender;

import java.util.Optional;

@Section(route = "randomtp", aliases = { "rtp" })
@Permission("eternalcode.command.randomtp")
public class RandomTpCommand {

    private final Game game;
    private final TeleportGameRepository repository;
    private final TeleportService teleportService;
    private final CdnPluginConfig pluginConfig;

    public RandomTpCommand(Game game, TeleportGameRepository repository, TeleportService teleportService, CdnPluginConfig pluginConfig) {
        this.game = game;
        this.repository = repository;
        this.teleportService = teleportService;
        this.pluginConfig = pluginConfig;
    }

    @Execute
    public void execute(LiteSender sender, Profile profile) {
        this.teleportService.teleportProfile(profile, profile.getUniverse(), result -> sender.sendMessage(result.isSuccess() ? pluginConfig.onTeleport : pluginConfig.onTeleportFail));
    }

    @Execute(route = "create", required = 2)
    @Permission("eternalcode.command.randomtp.create")
    public void set(LiteSender sender, Profile profile, @Arg(0) @Name("name") String name, @Arg(1) TeleportType type) {
        Optional<Position> optionalPosition = profile.getTargetPosition();

        if (optionalPosition.isEmpty()) {
            sender.sendMessage(pluginConfig.onNoPosition);
            return;
        }

        Position target = optionalPosition.get();

        this.repository.saveTeleport(name, target, type.getName());
        this.game.setBlockType(target, type.getCoreType());

        BlockType buttonType = type.getButtonBlock();

        this.setButton(target, buttonType, -1, 0);
        this.setButton(target, buttonType, 1, 0);
        this.setButton(target, buttonType, 0, -1);
        this.setButton(target, buttonType, 0, 1);

        sender.sendMessage(pluginConfig.onCreate);
    }

    @Execute(route = "delete", required = 1)
    @Permission("eternalcode.command.randomtp.delete")
    public void delete(LiteSender sender, @Arg(0) @Name("name") String name) {
        this.repository.deleteTeleport(name);
        sender.sendMessage(pluginConfig.onDelete);
    }

    @IgnoreMethod
    private void setButton(Position target, BlockType buttonType, int x, int z) {
        this.game.setBlockType(target.add(x, 0, z), buttonType);
        this.game.getButtonIfPresent(target.add(x, 0, z)).ifPresent(button -> button.setPillar(target));
    }

}
