package org.ose.javase.jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ClassInfo {
    public static void main(String[] args) throws Exception {
        Class<A> c = A.class;

        System.out.println(c);
        System.out.println(c.getName());
        System.out.println(c.getSimpleName());
        System.out.println(c.getCanonicalName());
        System.out.println(c.getSuperclass());

        for (Class<?> interf : c.getInterfaces()) {
            System.out.println(interf);
        }

        Constructor<A> cons = c.getConstructor(String.class, String.class);
        Method f = c.getMethod("f", int.class);
        A a = cons.newInstance("s1", "s2");
        f.invoke(a, 100);

        try {
            @SuppressWarnings("unused")
            A a2 = c.newInstance();
        } catch (IllegalAccessException e) {
            // class or default constructor not accessible
            System.err.println(e);
        } catch (InstantiationException e) {
            // no default constructor
            System.err.println(e);
        }

        try {
            @SuppressWarnings("unused")
            B b = B.class.newInstance();
        } catch (IllegalAccessException e) {
            System.err.println(e);
        } catch (InstantiationException e) {
            System.err.println(e);
        }
    }
}

class A implements I1, I2 {
    public A(String s1, String s2) {
    }

    public void f(int n) {
        System.out.println("f(): " + n);
    }
}

class B {
    private B() {
    }
}

interface I1 {
}

interface I2 {
}
