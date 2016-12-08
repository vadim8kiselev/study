package com.kiselev.library.view.impl;

import com.kiselev.library.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView implements View {

    private BufferedReader stream;

    public ConsoleView() {
        stream = new BufferedReader(new InputStreamReader(System.in));
    }

    public void update(String page) {
        System.out.println(page);
    }

    public String input() {
        try {
            return stream.readLine();
        } catch (IOException error) {
            return "";
        }
    }
}
