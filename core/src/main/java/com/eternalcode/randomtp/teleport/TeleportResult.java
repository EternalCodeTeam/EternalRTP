package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.profile.Profile;
import com.eternalcode.randomtp.shared.Position;

public class TeleportResult {

    private final Profile profile;
    private final Position to;
    private final boolean success;

    private TeleportResult(Profile profile, Position to, boolean success) {
        this.profile = profile;
        this.to = to;
        this.success = success;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean isFailure() {
        return !this.success;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public Position getTo() {
        return this.to;
    }

    public static TeleportResult success(Profile profile, Position position) {
        return new TeleportResult(profile, position, true);
    }

    public static TeleportResult failure(Profile profile) {
        return new TeleportResult(profile, Position.ZERO, false);
    }

}
