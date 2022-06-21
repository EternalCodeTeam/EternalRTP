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
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public Profile getProfile() {
        return profile;
    }

    public Position getTo() {
        return to;
    }

    public static TeleportResult success(Profile profile, Position position) {
        return new TeleportResult(profile, position, true);
    }

    public static TeleportResult failure(Profile profile) {
        return new TeleportResult(profile, Position.ZERO, false);
    }

}
