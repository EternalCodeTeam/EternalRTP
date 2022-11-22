package com.eternalcode.randomtp.command;

import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.shared.Placeholders;
import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.teleport.TeleportService;
import com.eternalcode.randomtp.teleport.game.TeleportGame;
import com.eternalcode.randomtp.teleport.game.TeleportGameRepository;
import com.eternalcode.randomtp.teleport.game.TeleportType;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import dev.rollczi.litecommands.platform.LiteSender;
import dev.rollczi.litecommands.suggestion.Suggest;
import panda.std.Option;

import java.util.Optional;

@Route(name = "randomtp", aliases = { "rtp" })
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
    public void set(
            LiteSender sender,
            Profile profile,
            @Arg @Name("name") @Suggest({ "nazwa_teleportu", "siema" })
            String name,
            @Arg
            TeleportType type
    ) {
        Optional<Position> optionalPosition = profile.getTargetPosition();

        if (optionalPosition.isEmpty()) {
            sender.sendMessage(pluginConfig.onNoPosition);
            return;
        }

        Position target = optionalPosition.get();

        Option<TeleportGame> teleport = this.repository.getTeleport(name);

        if (teleport.isPresent()) {
            sender.sendMessage(pluginConfig.onTeleportExists);
            return;
        }

        this.repository.saveTeleport(name, target, type.getName());
        // TODO: Move to other class {START}
        // Add e.g. TeleportGameService and move this code there
        this.game.setBlockType(target, type.getCoreType());

        BlockType buttonType = type.getButtonBlock();

        this.setButton(target, buttonType, -1, 0);
        this.setButton(target, buttonType, 1, 0);
        this.setButton(target, buttonType, 0, -1);
        this.setButton(target, buttonType, 0, 1);
        // TODO: Move to other class {END}

        sender.sendMessage(pluginConfig.onCreate);
    }

    @Execute(route = "delete", required = 1)
    @Permission("eternalcode.command.randomtp.delete")
    public void delete(LiteSender sender, @Arg TeleportGame teleport) {
        this.repository.deleteTeleport(teleport.getName());
        sender.sendMessage(pluginConfig.onDelete);
    }

    @Execute(route = "list")
    @Permission("eternalcode.command.randomtp.list")
    public void list(LiteSender sender) {
        for (TeleportGame teleport : this.repository.getTeleports()) {
            String message = Placeholders.format(pluginConfig.teleportInfo, teleport);

            sender.sendMessage(message);
        }
    }

    // TODO: Move to other class
    private void setButton(Position target, BlockType buttonType, int x, int z) {
        this.game.setBlockType(target.add(x, 0, z), buttonType);
        this.game.getButtonIfPresent(target.add(x, 0, z)).ifPresent(button -> button.setPillar(target));
    }

}
