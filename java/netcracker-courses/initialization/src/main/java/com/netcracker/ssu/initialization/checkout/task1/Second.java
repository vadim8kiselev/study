package com.netcracker.ssu.initialization.checkout.task1;
/*





class SecondParent {
    {
        System.out.println("1");
    }
    static {
        System.out.println("2");
    }
    private String method() {
        System.out.println("3");
        return "3";
    }
    private static String staticMethod() {
        System.out.println("4");
        return "4";
    }
    public SecondParent() {
        System.out.println("5");
    }
}
public class Second extends SecondParent {
    {
        System.out.println("6");
    }
    static {
        System.out.println("7");
    }
    private String method() {
        System.out.println("8");
        return "8";
    }
    private static String staticMethod() {
        System.out.println("9");
        return "9";
    }
    public Second() {
        System.out.println("10");
    }
    public static void main(String[] args) {
        new Second();
    }
}

Variants:
1. 1 2 3 4 5 6 7 8 9 10
2. 2 4 7 9 1 3 5 6 8 10
3. 2 7 1 5 6 10
4. Ошибка компиляции

*/
