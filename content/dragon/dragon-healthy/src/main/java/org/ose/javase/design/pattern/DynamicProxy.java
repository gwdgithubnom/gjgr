package org.ose.javase.design.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

public class DynamicProxy {
    public static void main(String[] args) {
        RealObject real = new RealObject();

        // Proxy.newProxyInstance(classloader, interfaces, handler);
        // param 1: Classloader loader
        // param 2: Class<?>[] interfaces
        // param 3: InvocationHandler h
        Interf proxy = (Interf) Proxy.newProxyInstance(Interf.class.getClassLoader(),
            new Class<?>[] { Interf.class }, new MyInvocationHandler(real));
        proxy.doSomething("doSomething");

        // cglib
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealObject.class);
        enhancer.setCallbacks(new Callback[] { new MyMethodInterceptor(), NoOp.INSTANCE });
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                return ("doSomethingWithoutProxy".equals(method.getName())) ? 1 : 0;
            }
        });
        RealObject proxy2 = (RealObject) enhancer.create();
        proxy2.doSomething("doSomething2");
        proxy2.doSomethingWithoutProxy("doSomething2");
    }

    private static class RealObject implements Interf {
        public RealObject() {
        }

        @Override
        public void doSomething(String str) {
            System.out.println(String.format("RealObject.doSomething(%s)", str));
        }

        public void doSomethingWithoutProxy(String str) {
            System.out.println(String.format("RealObject.doSomethingWithoutProxy(%s)", str));
        }
    }

    /* ==================== InvocationHandler ==================== */

    private static interface Interf {
        void doSomething(String str);
    }

    private static class MyInvocationHandler implements InvocationHandler {
        private Object proxied;

        public MyInvocationHandler(Object proxied) {
            this.proxied = proxied;
        }

        // call on a proxy instance will be forwarded here
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                                                                        throws IllegalArgumentException,
                                                                        IllegalAccessException,
                                                                        InvocationTargetException {
            // intermediary tasks
            System.out.println("[InvocationHandler] proxy: " + proxy.getClass());
            System.out.println("[InvocationHandler] method: " + method);
            System.out.println("[InvocationHandler] args: " + Arrays.toString(args));

            // actual call
            return method.invoke(proxied, args);
        }
    }

    /* ==================== cglib ==================== */
    private static class MyMethodInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                                                                                            throws Throwable {
            System.out.println("[MethodInterceptor] proxy: " + proxy.getClass());
            System.out.println("[MethodInterceptor] method: " + method);
            System.out.println("[MethodInterceptor] args: " + Arrays.toString(args));

            return proxy.invokeSuper(obj, args);
        }
    }
}
