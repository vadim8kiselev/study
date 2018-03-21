package com.ssu.kiselev.task4;

import com.ssu.kiselev.Task;
import com.ssu.kiselev.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.Random;

public class Task4 extends JFrame implements Task {

    private static final int windowHeight = 530;

    private static final int windowWidth = 500;

    @Override
    public Task solve() {
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
