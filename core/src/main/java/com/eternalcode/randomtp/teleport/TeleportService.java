package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Game;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.shared.Universe;
import panda.std.Blank;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TeleportService {

    private final Set<TeleportFilter> filters = new HashSet<>();
    private final TeleportAlgorithm algorithm;
    private final TeleportRange range;
    private final TeleportCorrector preCorrector;
    private final TeleportCorrector postCorrector;
    private final Scheduler scheduler;
    private final Game game;

    private TeleportSettings settings = TeleportSettings.DEFAULT;

    public TeleportService(TeleportAlgorithm algorithm, TeleportRange range, TeleportCorrector preCorrector, TeleportCorrector postCorrector, Scheduler scheduler, Game game) {
        this.algorithm = algorithm;
        this.range = range;
        this.preCorrector = preCorrector;
        this.postCorrector = postCorrector;
        this.scheduler = scheduler;
        this.game = game;
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

    public void teleportProfile(Profile profile, Universe universe, TeleportCallback callback) {
        this.teleportProfiles(Set.of(profile), universe, callback);
    }

    public CompletableFuture<Boolean> teleportProfiles(Collection<Profile> profiles, Universe universe, TeleportCallback callback) {
        CompletableFuture<Boolean> result = new CompletableFuture<>();

        this.findPosition(universe, this.settings.getAttempts()).whenComplete((position, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                profiles.forEach(profile -> callback.call(TeleportResult.failure(profile)));
                result.complete(false);
                return;
            }

            if (position.isEmpty()) {
                profiles.forEach(profile -> callback.call(TeleportResult.failure(profile)));
                result.complete(false);
                return;
            }

            scheduler.runSync(() -> {
                for (Profile profile : profiles) {
                    profile.teleport(position.get()).thenAccept(isSuccess -> {
                        if (isSuccess) {
                            callback.call(TeleportResult.success(profile, position.get()));
                            return;
                        }

                        callback.call(TeleportResult.failure(profile));
                    });
                }

                result.complete(true);
            });
        });

        return result;
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

        this.preCorrector.correct(position)
                .thenApply(this.game::getAsyncBlockState)
                .thenApplyAsync(CompletableFuture::join)
                .whenComplete((blockState, throwable) -> {
                    if (throwable != null) {
                        future.completeExceptionally(throwable);
                        return;
                    }

                    for (TeleportFilter filter : this.filters) {
                        if (!filter.check(blockState)) {
                            this.findPosition(universe, attempts - 1).whenComplete((find, error) -> {
                                if (error != null) {
                                    future.completeExceptionally(error);
                                    return;
                                }

                                future.complete(find);
                            });

                            return;
                        }
                    }

                    Position postCorrected = this.postCorrector.correct(blockState.getPosition()).join();

                    future.complete(Optional.of(postCorrected));
                }).whenComplete((blockState, throwable) -> {
                    if (throwable != null) {
                        future.completeExceptionally(throwable);
                    }
                });

        return future;
    }

}
