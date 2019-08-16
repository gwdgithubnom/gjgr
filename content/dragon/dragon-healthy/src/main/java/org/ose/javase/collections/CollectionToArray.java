package org.ose.javase.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionToArray {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<String>();
        c.add("111");
        c.add("222");
        c.add("333");

        // returns a new Object[]
        Object[] objs = c.toArray();
        System.out.println(Arrays.toString(objs));

        // If the list fits in the specified array, it is returned therein.
        // Otherwise, a new array is allocated with the runtime type of the
        // specified array and the size of this list.
        String[] strs1 = c.toArray(new String[0]);
        System.out.println(Arrays.toString(strs1));

        // the element in the array immediately following the end of the list is set to null.
        // (This is useful in determining the length of this collection only if
        // the caller knows that this collection does not contain any null elements)
        String[] strs2 = { "H", "e", "l", "l", "o", " ", "W", "o", "r", "l", "d" };
        c.toArray(strs2);
        System.out.println(Arrays.toString(strs2));
    }
}
