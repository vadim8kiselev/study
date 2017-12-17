package com.netcracker.ssu.initialization.order;

class ParentOfOrderOfInitialization {

    private static String parentStaticField = getParentStaticField();

    private static String getParentStaticField() {
        System.out.println("1: Parent static field");
        return "1";
    }

    private String parentObjectField = getParentObjectField();

    private String getParentObjectField() {
        System.out.println("2: Parent object field");
        return "2";
    }

    static {
        System.out.println("3: Parent static initialization block");
    }

    {
        System.out.println("4: Parent initialization block");
    }

    public ParentOfOrderOfInitialization() {
        System.out.println("5: Parent constructor");
    }
}



public class OrderOfInitialization extends ParentOfOrderOfInitialization {

    private static String staticField = getStaticField();

    private static String getStaticField() {
        System.out.println("6: Static field");
        return "6";
    }

    private String objectField = getObjectField();

    private String getObjectField() {
        System.out.println("7: Object field");
        return "7";
    }

    static {
        System.out.println("8: Static initialization block");
    }

    {
        System.out.println("8: Initialization block");
    }

    public OrderOfInitialization() {
        System.out.println("9: Constructor");
    }
}
