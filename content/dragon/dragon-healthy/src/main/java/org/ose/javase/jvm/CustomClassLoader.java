package org.ose.javase.jvm;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

import org.ose.javase.io.UrlDara;

// Load class files form URL
public class CustomClassLoader extends ClassLoader {
    private final String baseUrl;

    public CustomClassLoader(ClassLoader parent, String urlBase) {
        super(parent);
        this.baseUrl = urlBase;
    }

    public CustomClassLoader(String urlBase) {
        super();
        this.baseUrl = urlBase;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("CustomClassLoader.findClass()");

        byte[] bytecodes = null;
        try {
            bytecodes = loadClassBytes(name);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }

        Class<?> clazz = defineClass(name, bytecodes, 0, bytecodes.length);
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }

        return clazz;
    }

    private byte[] loadClassBytes(String name) throws IOException {
        String classUrl = baseUrl + name.replace(".", "/") + ".class";
        return UrlDara.readBytes(new URL(classUrl));
    }

    public static void main(String[] args) throws Throwable {
        CustomClassLoader customClassLoader = new CustomClassLoader("http://htyleo.com/test/");

        Class<?> clazz = customClassLoader.loadClass("com.htyleo.jvm.SimpleRuntimeInfo");
        Method method = clazz.getMethod("main", String[].class);
        method.invoke(null, (Object) new String[] {});
        System.out.println(clazz.getClassLoader());
    }
}
