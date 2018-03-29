package com.ssu.kiselev.task2;

import com.ssu.kiselev.Task;
import com.ssu.kiselev.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 1.2. Системы дифференциальных уравнений
 * <p>
 * Задача 14. Построить фазовый портрет системы дифференциальных
 * уравнений
 * <p>
 * dy/dt = x + a * y
 * dx/dt = a * x - y,
 * <p>
 * при − 0,2 < a < 0. Эксперимент повторить при a > 0 .
 */
public class Task2 extends JFrame implements Task {

    private static final int windowHeight = 600;

    private static final int windowWidth = 550;

    private List<List<Double>> xCoordinates = new ArrayList<>();

    private List<List<Double>> yCoordinates = new ArrayList<>();

    @Override
    public Task solve() {
        int[] xs = {180, -180, 180, -180};
        int[] ys = {180, -180, -180, 180};

        for (int index = 0; index < xs.length; index++) {
            double time = 0;

            double x = xs[index];
            double y = ys[index];

            List<Double> xInnerCoordinates = new ArrayList<>();
            List<Double> yInnerCoordinates = new ArrayList<>();

            while (time < 25) {
                final double a = -0.1;

                double newX = x + 0.01 * function(a, x, y);
                double newY = y + 0.01 * gunction(x, a, y);
                System.out.println("x : " + newX + " - y : " + newY);

                x = newX;
                y = newY;

                xInnerCoordinates.add(x + (windowWidth / 2));
                yInnerCoordinates.add(y + (windowHeight / 2));

                time += 0.01;
            }

            xCoordinates.add(xInnerCoordinates);
            yCoordinates.add(yInnerCoordinates);
        }

        return this;
    }

    private Double function(double a, double x, double y) {
        return a * x - y;
    }

    private Double gunction(double x, double a, double y) {
        return x + a * y;
    }

    @Override
    public void draw() {
        Frame.draw(this, new DrawingComponent(), windowHeight, windowWidth);
    }

    class DrawingComponent extends JPanel {

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;

            for (int index = 0; index < xCoordinates.size(); index++) {
                graphics2D.drawPolyline(generatePoints(xCoordinates.get(index), 0),
                        generatePoints(yCoordinates.get(index), windowHeight),
                        xCoordinates.get(index).size());
            }
        }

        private int[] generatePoints(Collection<Double> collection, Integer offset) {
            int index = 0;
            int[] coordinates = new int[collection.size()];
            for (Double value : collection) {
                coordinates[index++] = generatePoint(value, offset);
            }
            return coordinates;
        }

        private int generatePoint(Double value, Integer offset) {
            int point = value.intValue();
            if (!offset.equals(0)) {
                point = offset - point;
            }
            return point;
        }
    }
}
