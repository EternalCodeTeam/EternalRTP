package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.shared.Box;
import com.eternalcode.randomtp.shared.Position;
import com.eternalcode.randomtp.shared.Scheduler;
import com.eternalcode.randomtp.test.GameTest;
import com.eternalcode.randomtp.test.TestProfile;
import com.eternalcode.randomtp.test.TestScheduler;
import com.eternalcode.randomtp.shared.Universe;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeleportServiceTest {

    private final Scheduler scheduler = new TestScheduler();
    private final TeleportRange range = TeleportRange.of(Box.of(new Position(-100), new Position(100)));
    private final TeleportAlgorithm algorithm = new RandomTeleportAlgorithm();
    private final TeleportService service = new TeleportService(algorithm, range, TeleportCorrector.NONE, TeleportCorrector.NONE, scheduler, new GameTest());

    @Test
    public void test() throws InterruptedException {
        TestProfile testProfile = new TestProfile();
        CountDownLatch lock = new CountDownLatch(1);

        service.teleportProfile(testProfile, Universe.NONE, result -> {
            assertTrue(result.isSuccess());
            lock.countDown();
        });

        lock.await(2000, TimeUnit.MILLISECONDS);
        assertEquals(1, testProfile.getTeleports());
    }

    @Test
    public void testWithSimpleFilter() throws InterruptedException {
        TestProfile testProfile = new TestProfile();
        CountDownLatch lock = new CountDownLatch(1);

        service.registerFilter(position -> false);
        service.teleportProfile(testProfile, Universe.NONE, result -> {
            assertTrue(result.isFailure());
            lock.countDown();
        });

        lock.await(2000, TimeUnit.MILLISECONDS);
        assertEquals(0, testProfile.getTeleports());
    }

}
