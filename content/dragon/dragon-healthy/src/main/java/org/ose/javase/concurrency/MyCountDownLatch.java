package org.ose.javase.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyCountDownLatch {
    public static void main(String[] args) {
        final int NUM_PARTIAL_TASK = 5;
        final int NUM_WAITING_TASK = 5;

        ExecutorService es = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(NUM_PARTIAL_TASK);
        for (int i = 0; i < NUM_PARTIAL_TASK; i++) {
            es.execute(new PartialTask(latch));
        }
        for (int i = 0; i < NUM_WAITING_TASK; i++) {
            es.execute(new WaitingTask(latch));
        }
        es.shutdown();
    }
}

class PartialTask implements Runnable {
    private static int           nextId = 0;
    private static Random        rand   = new Random();

    private final int            id     = nextId++;
    private final CountDownLatch latch;                // shared latch

    public PartialTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
    }

    @Override
    public String toString() {
        return String.format("PartialTask %1$-3d", id);
    }

    private void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this + "completed");
    }
}

class WaitingTask implements Runnable {
    private static int           nextId = 0;

    private final int            id     = nextId++;
    private final CountDownLatch latch;            // shared latch

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
    }

    @Override
    public String toString() {
        return String.format("WaitingTask %1$-3d", id);
    }
}
