package org.ose.javase.java8;

public class InterfaceStaticMethod {
    public static void main(String[] args) {
        Interf.f();
    }
}

interface Interf {
    public static void f() {
        System.out.println("staticMethod()");
    }
}
