package com.eternalcode.randomtp.teleport.game;

import com.eternalcode.randomtp.TeleportTestFactory;
import com.eternalcode.randomtp.config.CdnPluginConfig;
import com.eternalcode.randomtp.config.CdnTeleportGameRepository;
import com.eternalcode.randomtp.shared.BlockState;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.teleport.TeleportService;
import com.eternalcode.randomtp.test.GameTest;
import com.eternalcode.randomtp.test.TestProfile;
import org.junit.jupiter.api.Test;

class TestGameController {

    private final GameTest game = new GameTest();
    private final TeleportService teleportService = TeleportTestFactory.builder()
            .game(game)
            .build();

    private final TeleportGameRepository teleportRepository = CdnTeleportGameRepository.empty(() -> {});
    private final CdnPluginConfig config = new CdnPluginConfig();

    private final TeleportGameController teleportGameController = new TeleportGameController(teleportService, teleportRepository, config, config, game);

    @Test
    void test() {
        TestProfile testProfile = new TestProfile();
        TeleportType normal = config.getType("normal").orElseThrow(AssertionError::new);

        BlockState pillar = new BlockState(Position.ONE, normal.getCoreType());
        BlockState button = new BlockState(Position.ZERO, normal.getButtonBlock());

        teleportRepository.saveTeleport("test", pillar.getPosition(), normal.getName());
        game.setButton(button, pillar);
        teleportGameController.handleRightClick(button, testProfile).join();

        testProfile.assertTeleportCount(1);
    }

}
