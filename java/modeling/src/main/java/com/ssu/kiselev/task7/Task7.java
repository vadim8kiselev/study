package com.ssu.kiselev.task7;

import com.ssu.kiselev.Task;
import com.ssu.kiselev.view.Frame;

import javax.swing.*;
import java.awt.*;

/**
 * 3.2. Сети массового обслуживания
 *
 * Задача 2. Сеть массового обслуживания состоит из двух последовательно
 * связанных СМО типа M | M |1| 0 и M | M |1. Из источника требований в пер-
 * вую СМО поступает пуассоновский поток требований с интенсивностью λ . За-
 * тем требования переходят во вторую СМО. После обслуживания во второй сис-
 * теме, требования возвращаются в источник. Построить имитационную модель
 * сети обслуживания. На основании 1000 выборочных значений оценить u1 и u2.
 * */
public class Task7 extends JFrame implements Task {

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
