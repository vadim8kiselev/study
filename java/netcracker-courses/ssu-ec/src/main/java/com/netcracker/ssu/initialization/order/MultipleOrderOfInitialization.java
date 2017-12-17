package com.netcracker.ssu.initialization.order;

class MultipleParentOfOrderOfInitialization {

    private static String parentStaticField1 = getParentStaticField1();

    private static String getParentStaticField1() {
        System.out.println("1.1: Parent static field");
        return "1";
    }

    private static String parentStaticField2 = getParentStaticField2();

    private static String getParentStaticField2() {
        System.out.println("1.2: Parent static field");
        return "1";
    }

    private String parentObjectField1 = getParentObjectField1();

    private String getParentObjectField1() {
        System.out.println("2.1: Parent object field");
        return "2";
    }

    private String parentObjectField2 = getParentObjectField2();

    private String getParentObjectField2() {
        System.out.println("2.2: Parent object field");
        return "2";
    }

    static {
        System.out.println("3.1: Parent static initialization block");
    }

    static {
        System.out.println("3.2: Parent static initialization block");
    }

    {
        System.out.println("4.1: Parent initialization block");
    }

    {
        System.out.println("4.2: Parent initialization block");
    }

    public MultipleParentOfOrderOfInitialization() {
        System.out.println("5: Parent constructor");
    }
}

public class MultipleOrderOfInitialization extends MultipleParentOfOrderOfInitialization {

    private static String staticField1 = getStaticField1();

    private static String getStaticField1() {
        System.out.println("6.1: Static field");
        return "6";
    }

    private static String staticField2 = getStaticField2();

    private static String getStaticField2() {
        System.out.println("6.2: Static field");
        return "6";
    }

    private String objectField1 = getObjectField1();

    private String getObjectField1() {
        System.out.println("7.1: Object field");
        return "7";
    }

    private String objectField2 = getObjectField2();

    private String getObjectField2() {
        System.out.println("7.2: Object field");
        return "7";
    }

    static {
        System.out.println("8.1: Static initialization block");
    }

    static {
        System.out.println("8.2: Static initialization block");
    }

    {
        System.out.println("9.1: Initialization block");
    }

    {
        System.out.println("9.2: Initialization block");
    }

    public MultipleOrderOfInitialization() {
        System.out.println("10: Constructor");
    }
}
