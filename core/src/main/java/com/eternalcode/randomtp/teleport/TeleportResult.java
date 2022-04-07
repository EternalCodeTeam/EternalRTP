package com.eternalcode.randomtp.teleport;

public class TeleportResult {

    private final boolean success;

    private TeleportResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public static TeleportResult success() {
        return new TeleportResult(true);
    }

    public static TeleportResult failure() {
        return new TeleportResult(false);
    }

}
