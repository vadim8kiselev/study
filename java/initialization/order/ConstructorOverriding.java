package com.netcracker.ssu.initialization.order;

class ParentConstructorOverriding {

    // Commented default constructor
    //public ParentConstructorOverriding() {
    //}

    public ParentConstructorOverriding(String key) {
        System.out.println(key);
    }
}

public class ConstructorOverriding extends ParentConstructorOverriding {

    public ConstructorOverriding(String key) {
        super(key);
        // code
    }

    public ConstructorOverriding(Integer integerKey) {
        super(integerKey.toString());
        // error, because of hidden super(integerKey);
    }
}
