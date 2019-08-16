package org.ose.javase;

public class Callback {
    public static void main(String[] args) {
        Caller caller1 = new Caller(new Callee1());
        caller1.call();

        Caller caller2 = new Caller(new Callee2().getCallbackReference());
        caller2.call();
    }
}

interface Incrementable {
    void increment();
}

class Caller {
    private Incrementable callbackReference;

    public Caller(Incrementable cbr) {
        this.callbackReference = cbr;
    }

    public void call() {
        this.callbackReference.increment();
    }
}

// without callback
class Callee1 implements Incrementable {
    private int i = 0;

    @Override
    public void increment() {
        System.out.println(++i);
    }
}

// with callback
class Callee2 {
    private int i = 0;

    public Incrementable getCallbackReference() {
        return new Closure();
    }

    private void increment() {
        System.out.println(++i);
    }

    // private inner class
    private class Closure implements Incrementable {
        @Override
        public void increment() {
            Callee2.this.increment();
        }
    }
}
