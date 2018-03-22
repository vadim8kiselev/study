package com.ssu.kiselev.task6;

import com.ssu.kiselev.Task;
import com.ssu.kiselev.view.Frame;

import javax.swing.*;
import java.awt.*;

/**
 * 3.1. Системы массового обслуживания
 *
 * Задача 2. Дана СМО типа M | M | 2. Построить имитационную модель
 * системы. На основании 1000 выборочных значений оценить u и n.
 * */
public class Task6 extends JFrame implements Task {

    private static final int windowHeight = 530;

    private static final int windowWidth = 500;

    @Override
    public Task solve() {
        throw new RuntimeException("This task is not done yet");
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
