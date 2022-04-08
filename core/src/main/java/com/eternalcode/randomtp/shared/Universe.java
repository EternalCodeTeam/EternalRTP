package com.eternalcode.randomtp.shared;

public class Universe {

    public static final Universe NONE = new Universe(null);

    private final String name;

    private Universe(String name) {
        this.name = name;
    }

    public String getName() {
        return this.isExisting() ? this.name : "__none__";
    }

    public boolean isExisting() {
        return this.name != null;
    }

    public static Universe of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        return new Universe(name);
    }

    public static Universe none() {
        return NONE;
    }

}
