package org.ose.javase.java8;

import java.util.Arrays;
/*
public class Lambdas {
    public static void main(String[] args) {
        String[] strs = { "efg", "hij", "abcba" };

        // type of e is inferred by the compiler
        Arrays.asList(strs).forEach(e -> System.out.println("lambda: " + e));

        @SuppressWarnings("unused")
        int x = 100;
        Arrays.asList(strs).forEach((String e) -> {
            // implicitly treat class members and local variables as final
            //x = 10; 
        });

        // functional interface Comparator
        Arrays.asList(strs).sort((e1, e2) -> e1.compareTo(e2));
        System.out.println(Arrays.toString(strs));

        // functional interface StringChecker
        print(strs, (String s) -> {
            String srev = new StringBuilder(s).reverse().toString();
            return s.equalsIgnoreCase(srev);
        });
    }

    private static void print(String[] strs, StringChecker c) {
        Arrays.asList(strs).forEach((String s) -> {
            if (c.check(s)) {
                System.out.println(s);
            }
        });
    }
}

@FunctionalInterface
interface StringChecker {
    public boolean check(String str);

    // default and static methods do not break the functional interface contract
    public default void defaultMethod() {
        System.out.println("defaultMethod()");
    }

    public static void staticMethod() {
        System.out.println("staticMethod()");
    }
}
*/