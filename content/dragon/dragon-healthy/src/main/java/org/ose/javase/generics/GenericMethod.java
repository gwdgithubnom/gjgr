package org.ose.javase.generics;

public class GenericMethod {
    public static <T> void g(T o) {
        System.out.println(o.getClass().getName());
    }

    public <T> void f(T o) {
        System.out.println(o.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethod.g("hello");
        GenericMethod.<String> g("hellp");

        GenericMethod gm = new GenericMethod();
        gm.<String> f("hi");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);
    }
}
