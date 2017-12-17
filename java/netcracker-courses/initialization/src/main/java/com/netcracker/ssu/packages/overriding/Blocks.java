package com.netcracker.ssu.packages.overriding;



public class Blocks {

    private String variable = "task1";

    public static void print() {
        String variable = "two";
        // code
        {
            System.out.println(variable);
        }
    }

    public static void main(String[] args) {
        new Blocks().print();
    }
}
