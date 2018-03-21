package com.ssu.kiselev;

import com.ssu.kiselev.task1.Task1;
import com.ssu.kiselev.task2.Task2;
import com.ssu.kiselev.task3.Task3;
import com.ssu.kiselev.task4.Task4;
import com.ssu.kiselev.task5.Task5;
import com.ssu.kiselev.task6.Task6;
import com.ssu.kiselev.task7.Task7;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.print("Enter code: ");
        String code = new Scanner(System.in).next();
        switch (code) {
            case "1":
                new Task1().solve().draw(); break;
            case "2":
                new Task2().solve().draw(); break;
            case "3":
                new Task3().solve().draw(); break;
            case "4":
                new Task4().solve().draw(); break;
            case "5":
                new Task5().solve().draw(); break;
            case "6":
                new Task6().solve().draw(); break;
            case "7":
                new Task7().solve().draw(); break;
            default:
                throw new RuntimeException("Error code");
        }
    }
}
