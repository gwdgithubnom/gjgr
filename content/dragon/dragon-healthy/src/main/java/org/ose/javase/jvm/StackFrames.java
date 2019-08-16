package org.ose.javase.jvm;

public class StackFrames {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.htyleo.javase.jvm.C");
        new C();
    }
}

class C {
    // <clinit>
    static {
        printStackTrace();
    }

    // <clinit>
    static String a = printStackTrace();

    // <init>
    public C() {
        printStackTrace();
    }

    // <init>
    String b = printStackTrace();

    private static String printStackTrace() {
        System.out.println("==========");
        StackTraceElement[] stes = new Throwable().getStackTrace();
        for (int i = 0; i < stes.length; i++) {
            System.out.println(String.format("StackTraceElement[%d]: class = %s, method = %s", i,
                stes[i].getClassName(), stes[i].getMethodName()));
        }
        System.out.println("==========");

        return null;
    }
}
