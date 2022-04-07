package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TeleportService {

    private final Scheduler scheduler;
    private final Set<TeleportFilter> filters = new HashSet<>();
    private final TeleportAlgorithm algorithm;
    private final TeleportRange range;
    private final HeightCorrector heightCorrector;
    private TeleportSettings settings = TeleportSettings.DEFAULT;

    public TeleportService(Scheduler scheduler, TeleportAlgorithm algorithm, TeleportRange range, HeightCorrector heightCorrector) {
        this.scheduler = scheduler;
        this.algorithm = algorithm;
        this.range = range;
        this.heightCorrector = heightCorrector;
    }

    public void teleportProfile(Profile profile, Universe universe, TeleportCallback callback) {
        this.findAsyncPosition(universe, this.settings.getAttempts()).thenAccept(position -> {
            if (position.isPresent()) {
                profile.teleport(position.get());
                callback.call(TeleportResult.success());
                return;
            }

            callback.call(TeleportResult.failure());
        });
    }

    public CompletableFuture<Optional<Position>> findAsyncPosition(Universe universe, int attempts) {
        if (attempts <= 0) {
            return CompletableFuture.completedFuture(Optional.empty());
        }

        CompletableFuture<Optional<Position>> future = new CompletableFuture<>();
        Box range = this.range.getRange(universe);
        Position position = this.algorithm.createPosition(range, universe);

        heightCorrector.correctPosition(position).thenAccept(correctedPosition -> {
            if (!range.contains(correctedPosition)) {
                throw new IllegalStateException("Position is outside of the range");
            }

            for (TeleportFilter filter : filters) {
                if (!filter.isValid(universe, correctedPosition)) {
                    this.findAsyncPosition(universe, attempts - 1).thenAccept(future::complete);
                    return;
                }
            }

            future.complete(Optional.of(correctedPosition));
        });

        return future;
    }

    public void registerFilter(TeleportFilter filter) {
        this.filters.add(filter);
    }

    public TeleportSettings getSettings() {
        return settings;
    }

    public void setSettings(TeleportSettings settings) {
        this.settings = settings;
    }
}
