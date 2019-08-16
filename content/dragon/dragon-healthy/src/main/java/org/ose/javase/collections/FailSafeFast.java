package org.ose.javase.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailSafeFast {
    public static void main(String[] args) {
        System.out.println("fail safe");
        failSafe();
        System.out.println("fail fast");
        failFast();
    }

    public static void failSafe() {
        Collection<String> c = new CopyOnWriteArrayList<String>();
        c.add("1");
        Iterator<String> it = c.iterator(); // snapshot
        c.add("2"); // new c
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println();

        it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void failFast() {
        Collection<String> c = new ArrayList<String>();
        c.add("1");
        Iterator<String> it = c.iterator();
        c.add("2");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
