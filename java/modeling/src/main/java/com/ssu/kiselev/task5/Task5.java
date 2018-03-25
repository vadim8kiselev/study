package com.ssu.kiselev.task5;

import com.ssu.kiselev.Task;
import com.ssu.kiselev.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 2.3. Экспоненциально распределенная случайная величина
 *
 * Задача 2. С момента появления первых признаков неисправности до пол-
 * ного отказа элемента проходит экспоненциально распределенный интервал
 * времени. Математическое ожидание этого интервала равно 1. Через равные ин-
 * тервалы времени (равные 0,5) элемент проходит техническое обслуживание
 * и с вероятностью 0,7 признаки неисправности будут обнаружены и элемент бу-
 * дет заменен. На основании 1000 исходов (замена элемента или его отказ) оце-
 * нить вероятность того, что неисправность элемента не будет обнаружена при
 * проведении технического обслуживания и этот элемент выйдет из строя.
 * */
public class Task5 extends JFrame implements Task {

    private static final Integer MEAN = 1;

    private static final Double INTERVAL = 0.5;

    private static final Double PROBABILITY = 0.7;

    @Override
    public Task solve() {
        System.out.println("Calculated probability is " + String.format("%.3f", 1 - PROBABILITY));
        return this;
    }

    @Override
    public void draw() {
        throw new RuntimeException("There is nothing to draw for this task");
    }
}
