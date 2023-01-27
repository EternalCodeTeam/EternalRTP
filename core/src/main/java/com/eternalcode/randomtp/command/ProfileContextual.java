package com.eternalcode.randomtp.command;

import com.eternalcode.randomtp.profile.Profile;
import dev.rollczi.litecommands.command.Invocation;
import dev.rollczi.litecommands.contextual.Contextual;
import panda.std.Blank;
import panda.std.Option;
import panda.std.Result;

public class ProfileContextual<SENDER> implements Contextual<SENDER, Profile> {

    private final Extractor<SENDER> extractor;

    public ProfileContextual(Extractor<SENDER> extractor) {
        this.extractor = extractor;
    }

    @Override
    public Result<Profile, ?> extract(SENDER sender, Invocation<SENDER> invocation) {
        return this.extractor.extract(invocation).toResult(Blank.BLANK);
    }

    public interface Extractor<SENDER> {

        Option<Profile> extract(Invocation<SENDER> invocation);

    }

}
