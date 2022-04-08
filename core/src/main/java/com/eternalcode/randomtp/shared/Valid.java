package com.eternalcode.randomtp.shared;

public class Valid {

    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
