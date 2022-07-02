package com.eternalcode.randomtp.teleport;

import com.eternalcode.randomtp.TeleportTestFactory;
import com.eternalcode.randomtp.test.TestProfile;
import com.eternalcode.randomtp.shared.Universe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeleportServiceTest {

    private final TeleportService service = TeleportTestFactory.builder()
            .build();

    @Test
    public void test() throws InterruptedException {
        TestProfile testProfile = new TestProfile();

        service.teleportProfile(testProfile, Universe.NONE, result -> {
            assertTrue(result.isSuccess());
        }).join();

        testProfile.assertTeleportCount(1);
    }

    @Test
    public void testWithSimpleFilter() throws InterruptedException {
        TestProfile testProfile = new TestProfile();

        service.registerFilter(position -> false);
        service.teleportProfile(testProfile, Universe.NONE, result -> {
            assertTrue(result.isFailure());
        }).join();

        testProfile.assertTeleportCount(0);
    }

}
