package com.eternalcode.randomtp.shared;

public interface Scheduler {

    void runSync(Runnable runnable);

    void runAsync(Runnable runnable);

}
