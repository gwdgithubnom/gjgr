package org.ose.javase.generics;

public class Bounds {
    public static void main(String[] args) {
        G<Sub> g = new G<Sub>(new Sub());
        g.f();
    }
}

class G<T extends A & B & C> {
    private T item;

    public G(T item) {
        this.item = item;
    }

    public void f() {
        item.fa();
        item.fb();
        item.fc();
    }
}

class A {
    public void fa() {
        System.out.println("A.fa()");
    }
}

interface B {
    public void fb();
}

interface C {
    public void fc();
}

class Sub extends A implements B, C {
    public void fa() {
        System.out.println("Sub.fa()");
    }

    public void fb() {
        System.out.println("fb()");
    }

    public void fc() {
        System.out.println("fc()");
    }
}
