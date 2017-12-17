package com.netcracker.ssu.initialization.uml;

interface Interface1 {
    void method1();
}

interface Interface2 {
    void method2();
}

class Parent {

    public String publicField;

    protected String protectedField;

    private String privateField;
}

public class Class extends Parent implements Interface1, Interface2 {

    public void method1() {

    }

    public void method2() {

    }
}
