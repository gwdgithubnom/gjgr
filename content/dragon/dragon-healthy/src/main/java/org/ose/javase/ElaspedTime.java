package org.ose.javase;

public class ElaspedTime {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        f();
        long endTime = System.nanoTime();
        double elapsedTime = ((double) (endTime - startTime)) / 1000000000; // seconds
        System.out.println(elapsedTime);
    }

    private static void f() {
        double x = 1.0;
        for (int i = 0; i < 100000; i++) {
            x = 1 / (Math.exp(x) + x);
        }
    }
}
