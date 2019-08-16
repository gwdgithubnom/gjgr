package org.ose.javase.generics;

public class Tuple {
    public static <U, V> TwoTuple<U, V> twoTuple(U u, V v) {
        return new TwoTuple<U, V>(u, v);
    }

    public static class TwoTuple<U, V> {
        private U u;
        private V v;

        public TwoTuple(U u, V v) {
            this.u = u;
            this.v = v;
        }

        public U getU() {
            return u;
        }

        public V getV() {
            return v;
        }
    }
}
