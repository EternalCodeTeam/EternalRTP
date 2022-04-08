package com.eternalcode.randomtp.command;

import com.eternalcode.randomtp.profile.Profile;
import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.bind.Parameter;

import java.util.Optional;

public class ProfileParameter implements Parameter {

    private final Extractor extractor;

    public ProfileParameter(Extractor extractor) {
        this.extractor = extractor;
    }

    @Override
    public Object apply(LiteInvocation invocation) {
        return extractor.extract(invocation).orElseThrow();
    }

    public interface Extractor {

        Optional<Profile> extract(LiteInvocation invocation);

    }

}
