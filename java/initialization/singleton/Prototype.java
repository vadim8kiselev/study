package com.netcracker.ssu.initialization.singleton;

import java.util.UUID;

public class Prototype {

    private String state;

    public Prototype() {
        this.state = getClass().getSimpleName() + ": " + UUID.randomUUID().toString();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
