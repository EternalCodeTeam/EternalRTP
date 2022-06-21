package com.eternalcode.randomtp.teleport.game;

import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.BlockState;
import com.eternalcode.randomtp.shared.BlockType;
import com.eternalcode.randomtp.shared.Button;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Placeholders;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.teleport.TeleportService;

import java.util.Collection;
import java.util.Optional;

public class TeleportGameController {

    private final TeleportService teleportService;
    private final TeleportGameRepository repository;
    private final TeleportTypeRegistry registry;
    private final CdnPluginConfig config;
    private final Game game;

    public TeleportGameController(TeleportService teleportService, TeleportGameRepository repository, TeleportTypeRegistry registry, CdnPluginConfig config, Game game) {
        this.teleportService = teleportService;
        this.repository = repository;
        this.registry = registry;
        this.config = config;
        this.game = game;
    }

    public void handleRightClick(BlockState blockState, Profile by) {
        Position position = blockState.getPosition();
        BlockType blockType = blockState.getBlockType();

        Optional<Button> buttonOptional = game.getButtonIfPresent(position);

        if (buttonOptional.isEmpty()) {
            return;
        }

        Button button = buttonOptional.get();

        BlockState pillarState = button.getPillar();
        Position pillarPosition = pillarState.getPosition();

        for (TeleportGame teleport : this.repository.getTeleports()) {
            if (!teleport.getCenter().equals(pillarPosition)) {
                continue;
            }

            Optional<TeleportType> typeOptional = this.registry.getType(teleport.getType());

            if (typeOptional.isEmpty()) {
                continue;
            }

            TeleportType type = typeOptional.get();

            if (!type.getButtonBlock().equals(blockType)) {
                continue;
            }

            if (type.requiredPlate() && !game.getBlockType(by.getPosition()).equals(type.getPlateBlock())) {
                continue;
            }

            if (type.isGroup()) {
                Collection<Profile> profiles = game.getNearbyProfiles(teleport.getCenter(), type.getRadius());

                this.teleportService.teleportProfiles(profiles, type.getUniverse(), result -> {
                    if (result.isFailure()) {
                        return;
                    }

                    String message = Placeholders.format(config.onGroupTeleport, result.getTo());

                    result.getProfile().sendMessage(message);
                }).whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                    }

                    if (throwable != null || !result) {
                        by.sendMessage(config.onTeleportFail);
                    }
                });
                return;
            }

            this.teleportService.teleportProfile(by, type.getUniverse(), result -> {
                by.sendMessage(result.isSuccess() ? Placeholders.format(config.onTeleport, result.getTo()) : config.onTeleportFail);
            });
            return;
        }
    }

}
