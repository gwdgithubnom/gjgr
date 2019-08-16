package org.ose.javase.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ArrayToCollection {
    public static void main(String[] args) {
        Integer[] a = { 6, 7, 8, 9, 10 };

        // static method, preferred
        Collection<Integer> c1 = new ArrayList<Integer>();
        Collections.addAll(c1, 1, 2, 3, 4, 5);
        Collections.addAll(c1, a);

        // member method, can only take an argument of another Collection object
        Collection<Integer> c2 = new ArrayList<Integer>();
        c2.addAll(Arrays.asList(a));

        // slow
        Collection<Integer> c3 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(c3);

        // backed by array, cannot be resized
        Collection<Integer> c4 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(c4);
    }
}
