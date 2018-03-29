package com.ssu.kiselev.task6;

import com.ssu.kiselev.Task;

/**
 * 3.1. Системы массового обслуживания
 * <p>
 * Задача 2. Дана СМО типа M | M | 2. Построить имитационную модель
 * системы. На основании 1000 выборочных значений оценить u и n.
 */
public class Task6 implements Task {

    @Override
    public Task solve() {

        int COUNT_OF_TASKS = 1000;

        int QUEUE_SIZE = 1_000_000;

        int DEVICE_COUNT = 2;

        double LAMBDA = 0.3;   // speed of arriving new tasks

        double U = 0.3;        // (nu) speed of handle tasks

        new Service(COUNT_OF_TASKS, QUEUE_SIZE, DEVICE_COUNT, LAMBDA, U).run();
        return this;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}
