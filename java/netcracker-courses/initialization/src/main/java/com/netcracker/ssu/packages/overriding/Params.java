package com.netcracker.ssu.packages.overriding;





class ParamsParent {

    private String field;

    public ParamsParent(String field) {
        this.field = field;
    }

    public void print() {
        System.out.println(field);
    }
}

public class Params extends ParamsParent {

    public Params(String field) {
        super(field);
    }

    public static void main(String[] args) {
        Params params = new Params("Hello, World!!!");
        params.print();
    }
}
