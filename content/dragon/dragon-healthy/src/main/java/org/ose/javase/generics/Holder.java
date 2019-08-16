package org.ose.javase.generics;

public class Holder<T> {
    private T o;

    public Holder(T o) {
        this.o = o;
    }

    public void set(T o) {
        this.o = o;
    }

    public T get() {
        return o;
    }

    public static void main(String[] args) {
        Holder<String> holder = new Holder<String>("Test");
        holder.set("Test again"); // Type checking at compile time.
        String s = holder.get(); // Insert casting code at compile time: Object -> String
        System.out.println(s);
    }
}

interface GenericInterf<T> {
    public T next();
}
