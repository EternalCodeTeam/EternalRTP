package com.eternalcode.randomtp;

import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.teleport.RandomTeleportAlgorithm;
import com.eternalcode.randomtp.teleport.TeleportAlgorithm;
import com.eternalcode.randomtp.teleport.TeleportCorrector;
import com.eternalcode.randomtp.teleport.TeleportRange;
import com.eternalcode.randomtp.teleport.TeleportService;
import com.eternalcode.randomtp.test.GameTest;
import com.eternalcode.randomtp.test.TestScheduler;

public class TeleportTestFactory {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private TeleportRange range = TeleportRange.of(Box.of(new Position(0), new Position(10)));
        private TeleportAlgorithm algorithm = new RandomTeleportAlgorithm();
        private TeleportCorrector preCorrector = TeleportCorrector.NONE;
        private TeleportCorrector postCorrector = TeleportCorrector.NONE;
        private Game game = new GameTest();
        private Scheduler scheduler = new TestScheduler();

        public Builder range(Box range) {
            this.range = TeleportRange.of(range);
            return this;
        }

        public Builder algorithm(TeleportAlgorithm algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public Builder preCorrector(TeleportCorrector preCorrector) {
            this.preCorrector = preCorrector;
            return this;
        }

        public Builder postCorrector(TeleportCorrector postCorrector) {
            this.postCorrector = postCorrector;
            return this;
        }

        public Builder scheduler(Scheduler scheduler) {
            this.scheduler = scheduler;
            return this;
        }

        public Builder game(Game game) {
            this.game = game;
            return this;
        }

        public TeleportService build() {
            return new TeleportService(algorithm, range, preCorrector, postCorrector, scheduler, game);
        }

    }

}
