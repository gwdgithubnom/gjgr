package org.ose.javase.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught: " + e);
    }

    public static void main(String[] args) {
        // set default exception handler
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        // set per-thread exception handler
        ExecutorService es = Executors
            .newCachedThreadPool(new MyUncaughtExceptionHandlerThreadFactory());
        es.execute(new ExceptionTask());
        es.shutdown();
    }
}

class ExceptionTask implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("MyRuntimeException");
    }
}

class MyUncaughtExceptionHandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return t;
    }
}
