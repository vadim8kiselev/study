package main;

import entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(new File("/tmp/chess.txt")).useDelimiter("\\n")) {

            String name = input.next().trim();
            Unit figure = Unit.valueOf(name.toUpperCase());
            Desk desk = new Desk(input);
            desk.showTable(figure);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}