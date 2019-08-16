package org.ose.javase.jvm;

import static org.ose.javase.jvm.PrintUtil.printlnReturnInt;

public class Initialization {
    public static void main(String[] args) throws ClassNotFoundException {
        Child.main();
    }
}

@SuppressWarnings("unused")
class Base {
    private static int x1 = printlnReturnInt("1. static Base.x1 initialized");

    protected int      j  = printlnReturnInt("4. protect Base.j initialized");
    private int        i  = printlnReturnInt("5. private Base.i initialized");

    public Base() {
        printlnReturnInt("6. Base constructor");
    }
}

@SuppressWarnings("unused")
class Child extends Base {
    private static int x2 = printlnReturnInt("2. static Child.x2 initialized");

    private int        k  = printlnReturnInt("7. private Child.k initialized");

    public Child() {
        printlnReturnInt("8. Child constructor");
    }

    public static void main() {
        printlnReturnInt("3. Child main");
        new Child();
        printlnReturnInt("9. End");
    }
}

class PrintUtil {
    public static int printlnReturnInt(String str) {
        System.out.println(str);
        return 0;
    }
}
