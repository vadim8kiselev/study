package com.netcracker.ssu.initialization;

import com.netcracker.ssu.initialization.order.MultipleOrderOfInitialization;
import com.netcracker.ssu.initialization.order.OrderOfInitialization;
import com.netcracker.ssu.initialization.singleton.Lazy;
import com.netcracker.ssu.initialization.singleton.Prototype;
import com.netcracker.ssu.initialization.singleton.Singleton;

public class InitializationMain {

    public static void main(String[] args) {

        initializationBlock();

        initializationClass();
    }

    private static void initializationBlock() {
        new OrderOfInitialization();
        System.out.println();
        new MultipleOrderOfInitialization();
    }

    private static void initializationClass() {
        initPrototype();
        initSingleton();
        initLazy();
    }

    private static void initPrototype() {
        System.out.println(new Prototype().getState());
        System.out.println(new Prototype().getState());
        System.out.println(new Prototype().getState());
        System.out.println();
    }

    private static void initSingleton() {
        System.out.println(Singleton.getInstance().getState());
        System.out.println(Singleton.getInstance().getState());
        System.out.println(Singleton.getInstance().getState());
        System.out.println();
    }

    private static void initLazy() {
        Lazy lazy = new Lazy();

        System.out.println(lazy.getState());
        System.out.println(lazy.getState());
        System.out.println(lazy.getState());
        System.out.println();
    }
}
