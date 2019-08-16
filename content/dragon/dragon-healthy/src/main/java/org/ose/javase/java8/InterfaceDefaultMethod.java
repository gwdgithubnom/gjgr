package org.ose.javase.java8;

public class InterfaceDefaultMethod {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.f());

        B b = new B();
        System.out.println(b.f());
    }
}

interface Defaultable {
    public default String f() {
        return "defaultMethod()";
    }
}

class A implements Defaultable {
}

class B implements Defaultable {
    @Override
    public String f() {
        return "B.f()";
    }
}
