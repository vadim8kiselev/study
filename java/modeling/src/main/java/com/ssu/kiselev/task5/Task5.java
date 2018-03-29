package com.ssu.kiselev.task5;

import com.ssu.kiselev.Task;

import javax.swing.*;
import java.util.Random;

/**
 * 2.3. Экспоненциально распределенная случайная величина
 * <p>
 * Задача 2. С момента появления первых признаков неисправности до пол-
 * ного отказа элемента проходит экспоненциально распределенный интервал
 * времени. Математическое ожидание этого интервала равно 1. Через равные ин-
 * тервалы времени (равные 0,5) элемент проходит техническое обслуживание
 * и с вероятностью 0,7 признаки неисправности будут обнаружены и элемент бу-
 * дет заменен. На основании 1000 исходов (замена элемента или его отказ) оце-
 * нить вероятность того, что неисправность элемента не будет обнаружена при
 * проведении технического обслуживания и этот элемент выйдет из строя.
 */
public class Task5 extends JFrame implements Task {

    private static final Integer COUNT = 1000;

    private static final Integer MEAN = 1;

    private static final Double INTERVAL = 0.5;

    private static final Double PROBABILITY = 0.7;

    @Override
    public Task solve() {

        int numberOfBrokenDetails = 0;

        for (int index = 0; index < COUNT; index++) {

            double time = -Math.log(new Random().nextDouble()) * MEAN;

            boolean isFixedOrChanged = false;

            for (int interval = 0; interval < (int) (time / INTERVAL); interval++) {
                if (new Random().nextDouble() < PROBABILITY) {
                    isFixedOrChanged = true;
                    break;
                }
            }

            if (!isFixedOrChanged) {
                numberOfBrokenDetails++;
            }
        }

        System.out.println("Calculated probability is " + String.format("%.3f", (numberOfBrokenDetails + 0.0) / COUNT));
        return this;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}
