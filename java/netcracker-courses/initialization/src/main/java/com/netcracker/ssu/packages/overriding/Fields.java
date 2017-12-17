package com.netcracker.ssu.packages.overriding;





class FieldsParent {

    public String field = "Parent field";

    public static String staticField = "Parent static field";

    public String getField() {
        return this.field;
    }

    public String getStaticField() {
        return this.staticField;
    }

    public void print() {
        System.out.println(getField());
        System.out.println(getStaticField());
        System.out.println();
    }
}

public class Fields extends FieldsParent {

    public String field = "Child field";

    public static String staticField = "Child static field";

    public String getField() {
        return this.field;
    }

    public String getStaticField() {
        return this.staticField;
    }

    public void print() {
        System.out.println(getField());
        System.out.println(getStaticField());
        System.out.println();
    }

    public static void main(String[] args) {
        FieldsParent object = new FieldsParent();
        //object.print();

        Fields child = new Fields();
        //child.print();
        child.additionalPrint();
    }





    public void additionalPrint() {
        System.out.println(field + "-" + super.field);
        System.out.println(staticField + "-" + super.staticField);
    }
}
