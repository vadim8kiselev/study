package com.kiselev.ssu.flanguage.task.holder;

import com.kiselev.ssu.flanguage.task.*;

import java.io.IOException;

public enum TaskHolder {

    FIRST(new FirstTask()),
    SECOND(new SecondTask()),
    THIRD(new ThirdTask()),
    FOURTH(new FourthTask());

    private AbstractTask task;

    TaskHolder(AbstractTask task) {
        this.task = task;
    }

    public void solve() {
        try {
            System.out.println("Task " + name().toLowerCase());
            task.solve();
        } catch (IOException exception) {
            System.err.println("Task failed with io exception: " + exception.getMessage());
        }
    }
}
