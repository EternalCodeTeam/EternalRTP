package com.eternalcode.randomtp.shared;

public interface Button {

    boolean isPowered();

    void setPowered(boolean powered);

    BlockState getPillar();

    void setPillar(Position pillar);

}
