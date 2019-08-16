package org.ose.javase.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            es.execute(new Accessor());
        }
        es.shutdown();
        TimeUnit.MILLISECONDS.sleep(50);
        es.shutdownNow();
    }
}

class MyTask {
    private static final Random               rand      = new Random();
    private static final AtomicInteger        nextId    = new AtomicInteger(0);
    // ThreadLocal instances are typically private static fields in classes 
    // that wish to associate state with a thread (e.g., a user ID or Transaction ID).
    private static final ThreadLocal<Integer> taskId    = new ThreadLocal<Integer>() {
                                                            @Override
                                                            protected Integer initialValue() {
                                                                return nextId.getAndIncrement();
                                                            }
                                                        };
    private static final ThreadLocal<Integer> taskValue = new ThreadLocal<Integer>() {
                                                            @Override
                                                            protected synchronized Integer initialValue() {
                                                                return rand.nextInt(1000);
                                                            }
                                                        };

    // no need to synchronize
    public static final int getTaskId() {
        // If the variable has no taskValue for the current thread,
        // it is first initialized to the taskValue returned by an invocation of the initialValue() method.
        return taskId.get();
    }

    public static final int getTaskValue() {
        return taskValue.get();
    }

    public static final void incrementValue() {
        taskValue.set(taskValue.get() + 1);
    }
}

class Accessor implements Runnable {
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("#" + MyTask.getTaskId() + ": " + MyTask.getTaskValue());
            MyTask.incrementValue();
            Thread.yield();
        }
    }
}
