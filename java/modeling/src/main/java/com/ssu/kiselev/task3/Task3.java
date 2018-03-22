package com.ssu.kiselev.task3;

import com.ssu.kiselev.Task;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 2.1. Равномерно распределенная случайная величина
 *
 * Задача 2. В мусорный бак может поместиться 10 пакетов с мусором.
 * В день в бак с равной вероятностью может поступить 0, 1, 2 или 3 пакета. По-
 * строить модель поступления пакетов в бак. На основании 1000 испытаний оце-
 * нить математическое ожидание числа дней до полного заполнения бака.
 */
public class Task3 extends JFrame implements Task {

    private List<Double> data = new ArrayList<>();

    @Override
    public Task solve() {
        for (int index = 0; index < 1000; index++) {
            int value = 0;
            double counter = 0;

            while (value < 10) {
                value += new Random().nextInt(4); // 0 1 2 3
                counter++;
            }
            data.add(counter);
        }

        double expectedMean = data.stream()
                .mapToDouble(Double::doubleValue)
                .sum() / data.size();

        System.out.println("Expected mean is " + expectedMean);
        return this;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}
