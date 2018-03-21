package com.ssu.kiselev.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;

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
