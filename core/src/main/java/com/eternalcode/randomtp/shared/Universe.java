package com.eternalcode.randomtp.shared;

import java.util.Objects;

public class Universe {

    public static final Universe NONE = new Universe(null);
    public static final Universe WORLD = new Universe("world");

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Universe universe)) {
            return false;
        }

        return this.name.equals(universe.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

}
