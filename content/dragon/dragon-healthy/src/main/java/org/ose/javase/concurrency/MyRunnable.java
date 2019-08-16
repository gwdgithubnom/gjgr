package org.ose.javase.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyRunnable {
    public static void main(String[] args) throws InterruptedException {
        // Thread(Runnable).start();
        for (int i = 1; i < 5; i++) {
            new Thread(new RunnableTask(i)).start();
        }

        TimeUnit.SECONDS.sleep(5);

        // ExecutorService.execute(Runnable);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 1; i < 5; i++) {
            es.execute(new RunnableTask(5));
        }
        es.shutdown();
    }
}

class RunnableTask implements Runnable {
    private int countDown;

    public RunnableTask(int countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread().toString() + ": " + countDown;
    }
}
