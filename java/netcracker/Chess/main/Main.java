package main;

import entities.figures.Unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(new File("/tmp/chess.txt")).useDelimiter("\\n")) {

            String name = input.next().trim();
            Unit figure = Unit.valueOf(name.toUpperCase());

            int row = 0;
            int column = 0;
            for (int index = 0; index < 8; index++) {
                String line = input.next().replaceAll(" ", "");
                if (line.indexOf(name.charAt(0)) != -1) {
                    row = index;
                    column = line.indexOf(name.charAt(0));
                    break;
                }
            }

            figure.printBoard(row, column);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
}

