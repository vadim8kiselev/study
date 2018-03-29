package com.ssu.kiselev.task1;

import com.ssu.kiselev.Task;
import com.ssu.kiselev.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1.1. Дифференциальные уравнения первого порядка
 * <p>
 * Задача 2. Температура свежевыпеченного хлеба равна 150 градусов.
 * До отправки в магазин хлеб остывает в помещении с постоянной температурой 20 градусов.
 * Требуется определить длительность времени охлаждения хлеба до 40 градусов.
 * <p>
 * Результат можно получить с использованием закона теплового излучения
 * dx/dt = -k * (x - a),
 * где x(t) – температура хлеба в момент времени t , a – температура воздуха
 * в помещении, k > 0 – коэффициент пропорциональности. Полагаем, что k = 0,02.
 */
public class Task1 extends JFrame implements Task {

    private static final int windowHeight = 530;

    private static final int windowWidth = 500;

    private static final Integer ROOM_DEGREE = 20;

    private static final Double COEFFICIENT = 0.02;

    private Double x = 150.0;

    private Integer y = 40;

    private Map<Double, Double> coordinates = new TreeMap<>();
    private Map<Double, Double> succeedPoints = new TreeMap<>();

    @Override
    public Task solve() {
        double time = 0;

        while (time < 150) {
            if (y.equals(x.intValue())) {
                System.out.println("Bread was cold for " + Math.round(x) + "t.");
                succeedPoints.put(time, x);
            }

            System.out.println("x: (" + String.format("%.2f", time) + ") - y: (" + String.format("%.2f", x) + ") ");
            coordinates.put(time, x);
            x = x + 0.01 * function(x);
            time += 0.01;
        }

        return this;
    }

    private Double function(Double currentDegree) {
        return -COEFFICIENT * (currentDegree - ROOM_DEGREE);
    }

    @Override
    public void draw() {
        Frame.draw(this, new DrawingComponent(), windowHeight, windowWidth);
    }

    class DrawingComponent extends JPanel {

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.drawPolyline(new int[]{0, 500, 495, 500, 495}, new int[]{500, 500, 495, 500, 505}, 5); // x
            graphics2D.drawPolyline(new int[]{5, 5, 0, 5, 10}, new int[]{508, 0, 5, 0, 5}, 5); //y

            graphics2D.drawPolyline(generatePoints(coordinates.keySet(), 0),
                    generatePoints(coordinates.values(), windowHeight),
                    coordinates.keySet().size());

            int[] yPoints = generatePoints(succeedPoints.values(), windowHeight);
            for (int yPoint : yPoints) {
                graphics2D.drawPolyline(new int[]{5, 500},
                        new int[]{yPoint, yPoint},
                        2);
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
            int point = new Double(value * 3).intValue() + 5;
            if (!offset.equals(0)) {
                point = offset - point;
            }
            return point;
        }
    }
}
