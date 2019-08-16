package com.kindlebird.core.entity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gwd on 11/7/2016.
 */
public enum Color {
    Default(-1) {
        public String getColor() {
            return "#00000";
        }
    },
    Black(0) {
        public String getColor() {
            return "#00000";
        }
    }, Red(1) {
        public String getColor() {
            return "#FF0000";
        }
    }, Green(2) {
        public String getColor() {
            return "#ee1f25";
        }
    }, Blue(3) {
        @Override
        public String getColor() {
            return "#69bd45";
        }
    }, Yellow(4) {
        public String getColor() {
            return "#FFFF00";
        }
    }, Orange(5) {
        public String getColor() {
            return "#FF8000";
        }
    }, Pink(6) {
        public String getColor() {
            return "#FF00FF";
        }
    }, Purple(7) {
        @Override
        public String getColor() {
            return "#800080";
        }
    }, Cyan(8) {
        @Override
        public String getColor() {
            return "#00FFFF";
        }
    }, Salmon(9) {
        @Override
        public String getColor() {
            return "#c67171";
        }
    };


    public abstract String getColor();

    private static int MAX = 9;
    private static final Map<Integer, Color> colorLookup = new HashMap<Integer, Color>();

    static {
        for (Color color : EnumSet.allOf(Color.class))
            colorLookup.put(color.id, color);
    }

    public static Color get(int id) {
        return colorLookup.get(id % MAX + 1);
    }

    private final int id;

    private Color(int id) {
        this.id = id;
    }
}
