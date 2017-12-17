package com.netcracker.ssu.initialization.singleton;

import java.util.UUID;

public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private String state;

    private Singleton() {
        this.state = getClass().getSimpleName() + ": " + UUID.randomUUID().toString();
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
