package com.ssu.kiselev.view;

import javax.swing.*;
import java.awt.*;

public class Frame {

    public static void draw(JFrame frame, JPanel panel, Integer windowHeight, Integer windowWidth) {
        JPanel jpanel = new JPanel(new BorderLayout());
        frame.setContentPane(jpanel);
        jpanel.setBackground(Color.white);

        jpanel.add(panel, BorderLayout.CENTER);
        frame.setSize(windowWidth, windowHeight);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
