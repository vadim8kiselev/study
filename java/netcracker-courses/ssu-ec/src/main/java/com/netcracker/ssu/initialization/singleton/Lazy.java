package com.netcracker.ssu.initialization.singleton;

import java.util.UUID;

public class Lazy {

    private String state;

    public Lazy() {
        // Empty constructor
    }

    public String getState() {
        if (state == null) {
            heavyOperation();
            state = getClass().getSimpleName() + ": " + UUID.randomUUID().toString();
        }

        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private void heavyOperation() {
        try {
            Thread.sleep(5000);
            System.out.println("Heavy operation was invoked...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
