package com.ssu.kiselev.task3;

import com.ssu.kiselev.Task;
import com.ssu.kiselev.view.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Task3 extends JFrame implements Task {

    private static final int windowHeight = 530;

    private static final int windowWidth = 500;

    private List<Integer> counters = new ArrayList<>();

    @Override
    public Task solve() {
        for (int index = 0; index < 1000; index++) {
            int value = 0;
            int counter = 0;

            while (value < 10) {
                value += new Random().nextInt(4); // 0 1 2 3
                counter++;
            }

            counters.add(counter);
        }

        //mean = sum([f[i] * x[i] for i in xrange(n)]) / sum(f)

        int max = counters.stream().mapToInt(Integer::intValue).max().orElse(0);
        int min = counters.stream().mapToInt(Integer::intValue).min().orElse(Integer.MAX_VALUE);

        System.out.println(Math.ceil((max - min + 0.0) / counters.size()));



        System.out.println("Math mean is " + 0);

        System.out.println(counters.stream().mapToInt(Integer::intValue)
                .average()
                .orElse(-1.0));
        return this;
    }

    @Override
    public void draw() {
        Frame.draw(this, new DrawingComponent(), windowHeight, windowWidth);
    }

    class DrawingComponent extends JPanel {

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.drawPolyline(new int[]{0, 500, 495, 500, 495},
                    new int[]{500, 500, 495, 500, 505}, 5); // x

            graphics2D.drawPolyline(new int[]{5, 5, 0, 5, 10},
                    new int[]{508, 0, 5, 0, 5}, 5); // y
        }
    }
}
