package com.netcracker.ssu.packages.imports;






import java.util.*;
import java.util.List;

public class WildcardInImportsIsBad {

    public static void method(List list) {
        list.add(new Object());
    }

    public static void method(Set set) {
        set.add(new Object());
    }

    public static void method(Queue queue) {
        queue.add(new Object());
    }

    // code
}
