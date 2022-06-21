package com.eternalcode.randomtp.command;

import dev.rollczi.litecommands.LiteInvocation;
import dev.rollczi.litecommands.argument.SingleArgumentHandler;
import dev.rollczi.litecommands.valid.ValidationCommandException;

import java.util.Arrays;
import java.util.List;

public class StringArg implements SingleArgumentHandler<String> {

    @Override
    public String parse(LiteInvocation invocation, String argument) throws ValidationCommandException {
        return argument;
    }

    @Override
    public List<String> tabulation(LiteInvocation invocation, String command, String[] args) {
        return Arrays.asList("nazwa_teleportu", "siema");
    }

}
