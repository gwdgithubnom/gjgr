package org.ose.javase.jvm;

public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader cl = ClassLoaderTest.class.getClassLoader();
        System.out.println(cl);
        try {
            Class.forName("ClassLoaderTest");
            Class.forName("ClassLoaderTest", true, cl);
            Class.forName("ClassLoaderTest", true, cl.getParent());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
