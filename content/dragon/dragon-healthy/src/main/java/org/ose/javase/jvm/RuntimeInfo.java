package org.ose.javase.jvm;

import java.util.ArrayList;

public class RuntimeInfo {
    private static final int MEGABYTES = 1024 * 1024;

    public static void main(String[] args) {
        System.out.println("Init");
        memInfo();

        ArrayList<Integer> objects = new ArrayList<Integer>();
        for (int i = 0; i < 10000000; i++) {
            objects.add(100);
        }

        System.out.println("After objects creation");
        memInfo();
    }

    private static void memInfo() {
        System.out.println("JVM max memory: " + Runtime.getRuntime().maxMemory() / MEGABYTES); // will not change
        System.out.println("JVM total memory: " + Runtime.getRuntime().totalMemory() / MEGABYTES);
        System.out.println("JVM free memory: " + Runtime.getRuntime().freeMemory() / MEGABYTES);
    }
}
