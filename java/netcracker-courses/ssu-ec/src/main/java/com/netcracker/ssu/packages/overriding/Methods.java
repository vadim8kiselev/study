package com.netcracker.ssu.packages.overriding;




class MethodsParent {

    public void method() { System.out.println("Parent method"); }

    public static void staticMethod() { System.out.println("Parent static method"); }
}

public class Methods extends MethodsParent {

    public void method() { System.out.println("Chlid method"); }

    public static void staticMethod() { System.out.println("Chlid static method"); }

    public static void main(String[] args) {
        MethodsParent parent = new MethodsParent();
        parent.method();
        parent.staticMethod();

        System.out.println();

        Methods child = new Methods();
        child.method();
        child.staticMethod();

        System.out.println();

        MethodsParent unexpected = new Methods();
        unexpected.method();
        unexpected.staticMethod();


        System.out.println();

        MethodsParent wtf = null;
        wtf.staticMethod();

        MethodsParent.staticMethod();
    }
}
