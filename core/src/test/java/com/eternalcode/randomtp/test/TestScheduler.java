package com.eternalcode.randomtp.test;

import com.eternalcode.randomtp.shared.Scheduler;

public class TestScheduler implements Scheduler {

    @Override
    public void runSync(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void runAsync(Runnable runnable) {
        runnable.run();
    }

}
