package com.ssu.kiselev.task3;

import com.ssu.kiselev.Task;
import com.ssu.kiselev.view.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import java.util.stream.Collectors;

public class Task3 extends JFrame implements Task {

    private List<Integer> data = new ArrayList<>();

    @Override
    public Task solve() {
        for (int index = 0; index < 1000; index++) {
            int value = 0;
            int counter = 0;

            while (value < 10) {
                value += new Random().nextInt(4); // 0 1 2 3
                counter++;
            }
            data.add(counter);
        }

        double mean = (data.stream()
                .mapToInt(Integer::intValue)
                .sum() + 0.0) / data.size();

        System.out.println("Math mean is " + mean);
        return this;
    }

    @Override
    public void draw() {
        // Skip for this task
    }
}
