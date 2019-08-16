package org.ose.javase;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

public class Enum {
    public static void main(String[] args) {
        Spiciness howHot = Spiciness.MEDIUM;
        System.out.println(howHot);
        System.out.println("name(): " + howHot.name());
        System.out.println("ordinal(): " + howHot.ordinal());
        System.out.println("values(): " + Arrays.toString(Spiciness.values()));

        // EnumSet
        EnumSet<Spiciness> enumSet = EnumSet.allOf(Spiciness.class);
        enumSet = EnumSet.noneOf(Spiciness.class);
        enumSet.add(Spiciness.NOT);
        enumSet.addAll(EnumSet.of(Spiciness.MILD, Spiciness.MEDIUM, Spiciness.HOT));
        enumSet.removeAll(EnumSet.range(Spiciness.MILD, Spiciness.HOT));
        enumSet = EnumSet.complementOf(enumSet);

        // EnumMap, key is from enum
        EnumMap<Spiciness, String> enumMap = new EnumMap<Spiciness, String>(Spiciness.class);
        enumMap.put(Spiciness.NOT, "not");
        enumMap.put(Spiciness.MILD, "mild");
    }

    private static enum Spiciness {
        NOT, MILD, MEDIUM, HOT, FLAMING
    }
}
