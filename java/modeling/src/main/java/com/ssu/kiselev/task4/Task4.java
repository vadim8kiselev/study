package com.ssu.kiselev.task4;

import com.ssu.kiselev.Task;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 2.2. Нормально распределенная случайная величина
 * <p>
 * Задача 2. Вес коробки с печеньями есть нормально распределенная слу-
 * чайная величина с параметрами µ =100 гр. и σ = 5 гр. Одна упаковка содержит
 * 10 коробок с печеньями. Построить модель формирования веса одной упаковки.
 * Оценить математическое ожидание и среднее квадратическое отклонение веса
 * одной упаковки на основании 1000 испытаний.
 */
public class Task4 extends JFrame implements Task {

    private static final Integer MEAN = 100;

    private static final Integer SIGMA = 5;

    private List<Double> data = new ArrayList<>();

    @Override
    public Task solve() {
        for (int index = 0; index < 1000; index++) {
            data.add(generatePack());
        }

        double expectedMean = data.stream()
                .mapToDouble(Double::doubleValue)
                .sum() / data.size(); // E[x] / n

        double standardDeviation = data.stream()
                .mapToDouble(value -> Math.pow(value - expectedMean, 2))
                .sum() / data.size(); // E[(x - m) ^ 2] / n

        System.out.println("Expected mean is " + expectedMean);
        System.out.println("Standard deviation is " + standardDeviation);
        return this;
    }

    private double generatePack() {
        return IntStream.range(1, 10).boxed()
                .mapToDouble(index -> generateBox())
                .sum();
    }

    private double generateBox() {
        return (IntStream.range(1, 12).boxed()
                .mapToDouble(index -> new Random().nextDouble())
                .sum() - 6) * SIGMA + MEAN;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}
