package com.ssu.kiselev.task7;

import com.ssu.kiselev.Task;

/**
 * 3.2. Сети массового обслуживания
 * <p>
 * Задача 2. Сеть массового обслуживания состоит из двух последовательно
 * связанных СМО типа M | M |1| 0 и M | M |1. Из источника требований в пер-
 * вую СМО поступает пуассоновский поток требований с интенсивностью λ . За-
 * тем требования переходят во вторую СМО. После обслуживания во второй сис-
 * теме, требования возвращаются в источник. Построить имитационную модель
 * сети обслуживания. На основании 1000 выборочных значений оценить u1 и u2.
 */
public class Task7 implements Task {

    // M | M | 1 | 0
    // M | M | 1 | ∞

    private static final int COUNT = 1000;      //общее колличество заданий

    private static final int QUEUE_SIZE = 1_000_000;    //размер очереди для ожидания

    private static final int DEVICE_COUNT = 1;  //количество обработчиков задач

    private static final double LAMBDA = 0.3;   //частота прихода новых заданий

    private static final double U = 0.3;        //скорость обработки заданий

    @Override
    public Task solve() {
        Service serviceTwo = new Service(null, QUEUE_SIZE, DEVICE_COUNT, LAMBDA, U);
        Service serviceOne = new Service(serviceTwo, 0, DEVICE_COUNT, LAMBDA, U);
        TaskManager manager = new TaskManager(COUNT, LAMBDA, U);
        manager.addService(serviceOne);
        manager.addService(serviceTwo);
        manager.run();
        return this;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}
