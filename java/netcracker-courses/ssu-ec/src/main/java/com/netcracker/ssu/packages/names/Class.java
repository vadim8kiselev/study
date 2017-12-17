package com.netcracker.ssu.packages.names;

import java.util.ArrayList;

public class Class {

    public static final Object[] objects = new Object[0];

    public static void main(String[] args) {

        java.util.List<List> list = new ArrayList<List>();


        java.lang.Class<?> clazz = Class.class;
        clazz = objects.getClass();

        String simpleName = clazz.getSimpleName();
        String name = clazz.getName();
        String canonicalName = clazz.getCanonicalName();

        System.out.println(simpleName);
        System.out.println(name);
        System.out.println(canonicalName);
    }
}
