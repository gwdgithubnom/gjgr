package org.ose.javase.design.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class Aop {
    public static void main(String[] args) {
        List<AbstractHandler> handlers = Arrays
            .asList(new BeforeHandlerImpl(), new AfterReturningHandlerImpl(),
                new AfterThrowingHandlerImpl(), new AfterHandlerImpl());

        FooInterf real1 = new Foo1();
        FooInterf proxy1 = (FooInterf) ProxyFactory.getProxy(real1, handlers);
        proxy1.bar();

        System.out.println();

        FooInterf real2 = new Foo2();
        FooInterf proxy2 = (FooInterf) ProxyFactory.getProxy(real2, handlers);
        proxy2.bar();
    }
}

interface FooInterf {
    public void bar();
}

class Foo1 implements FooInterf {
    @Override
    public void bar() {
        System.out.println("Foo1.bar()");
    }
}

class Foo2 implements FooInterf {
    @Override
    public void bar() {
        System.out.println("Foo2.bar()");
        throw new RuntimeException("Foo2.bar() RuntimeException");
    }
}

class ProxyFactory {
    public static Object getProxy(Object proxied, List<AbstractHandler> handlers) {
        Object proxy = proxied;

        // proxy chains
        for (AbstractHandler handler : handlers) {
            handler.setProxied(proxy);
            proxy = Proxy.newProxyInstance(proxied.getClass().getClassLoader(), proxied.getClass()
                .getInterfaces(), handler);
        }

        return proxy;
    }
}

abstract class AbstractHandler implements InvocationHandler {
    private Object proxied;

    public void setProxied(Object proxied) {
        this.proxied = proxied;
    }

    protected Object getProxied() {
        return proxied;
    }
}

abstract class AbstractBeforeHandler extends AbstractHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        handleBefore(proxy, method, args);
        return method.invoke(getProxied(), args);
    }

    protected abstract void handleBefore(Object proxy, Method method, Object[] args);
}

abstract class AbstractAfterHandler extends AbstractHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object result = method.invoke(getProxied(), args);
            return result;
        } finally {
            handleAfter(proxy, method, args);
        }
    }

    protected abstract void handleAfter(Object proxy, Method method, Object[] args);
}

abstract class AbstractAfterReturningHandler extends AbstractHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(getProxied(), args);
        handleAfterReturning(proxy, method, args);
        return result;
    }

    protected abstract void handleAfterReturning(Object proxy, Method method, Object[] args);
}

abstract class AbstractAfterThrowingHandler extends AbstractHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object result = method.invoke(getProxied(), args);
            return result;
        } catch (Exception e) {
            handleAfterThrowing(proxy, method, args, e);
            throw e;
        }
    }

    protected abstract void handleAfterThrowing(Object proxy, Method method, Object[] args,
                                                Exception e);
}

class BeforeHandlerImpl extends AbstractBeforeHandler {
    @Override
    public void handleBefore(Object proxy, Method method, Object[] args) {
        System.out.println("[handleBefore]");
    }
}

class AfterHandlerImpl extends AbstractAfterHandler {
    @Override
    public void handleAfter(Object proxy, Method method, Object[] args) {
        System.out.println("[handleAfter]");
    }
}

class AfterReturningHandlerImpl extends AbstractAfterReturningHandler {
    @Override
    public void handleAfterReturning(Object proxy, Method method, Object[] args) {
        System.out.println("[handleAfterReturning]");
    }
}

class AfterThrowingHandlerImpl extends AbstractAfterThrowingHandler {
    @Override
    public void handleAfterThrowing(Object proxy, Method method, Object[] args, Exception e) {
        System.out.println("[handleAfterThrowing] Exception: " + e);
    }
}
