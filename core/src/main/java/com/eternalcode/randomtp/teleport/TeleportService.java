package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Universe;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TeleportService {

    private final Scheduler scheduler;
    private final Set<TeleportFilter> filters = new HashSet<>();
    private final TeleportAlgorithm algorithm;
    private final TeleportRange range;
    private final TeleportPositionCorrector corrector;
    private TeleportSettings settings = TeleportSettings.DEFAULT;

    public TeleportService(Scheduler scheduler, TeleportAlgorithm algorithm, TeleportRange range, TeleportPositionCorrector corrector) {
        this.scheduler = scheduler;
        this.algorithm = algorithm;
        this.range = range;
        this.corrector = corrector;
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

    public CompletableFuture<Boolean> checkFilters(Position position) {
        return this.handleFilterIterator(position, new CompletableFuture<>(), filters.iterator());
    }

    public void teleportProfile(Profile profile, Universe universe, TeleportCallback callback) {
        this.teleportProfiles(Set.of(profile), universe, callback);
    }

    public void teleportProfiles(Collection<Profile> profiles, Universe universe, TeleportCallback callback) {
        this.findPosition(universe, this.settings.getAttempts()).thenAccept(position -> {
            if (position.isEmpty()) {
                profiles.forEach(profile -> callback.call(TeleportResult.failure(profile)));
                return;
            }

            for (Profile profile : profiles) {
                profile.teleport(position.get()).thenAccept(isSuccess -> {
                    if (isSuccess) {
                        callback.call(TeleportResult.success(profile));
                        return;
                    }

                    callback.call(TeleportResult.failure(profile));
                });
            }
        });
    }

    public CompletableFuture<Optional<Position>> findPosition(Universe universe, int attempts) {
        if (attempts <= 0) {
            return CompletableFuture.completedFuture(Optional.empty());
        }

        CompletableFuture<Optional<Position>> future = new CompletableFuture<>();
        Box range = this.range.getRange(universe);
        Position position = this.algorithm.createPosition(range, universe);

        if (!range.contains(position)) {
            throw new IllegalStateException("Position is outside of the range");
        }

        corrector.correctPosition(position).thenAccept(correctedPosition -> checkFilters(correctedPosition).thenAccept(isValid -> {
            if (isValid) {
                future.complete(Optional.of(correctedPosition));
                return;
            }

            findPosition(universe, attempts - 1).thenAccept(future::complete);
        }));

        return future;
    }

    private CompletableFuture<Boolean> handleFilterIterator(Position position, CompletableFuture<Boolean> future, Iterator<TeleportFilter> filterIterator) {
        if (!filterIterator.hasNext()) {
            future.complete(true);
            return future;
        }

        TeleportFilter filter = filterIterator.next();

        filter.check(position).thenAccept(isValid -> {
            if (isValid) {
                this.handleFilterIterator(position, future, filterIterator);
                return;
            }

            future.complete(false);
        });

        return future;
    }

}
