package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.profile.Profile;

public class TeleportResult {

    private final Profile profile;
    private final boolean success;

    private TeleportResult(Profile profile, boolean success) {
        this.profile = profile;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public static TeleportResult success(Profile profile) {
        return new TeleportResult(profile, true);
    }

    public static TeleportResult failure(Profile profile) {
        return new TeleportResult(profile, false);
    }

}
