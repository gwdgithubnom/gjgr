package org.ose.javase.concurrency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyCyclicBarrier {
    public static void main(String[] args) {
        final int NUM_PARTIAL_TASK = 5;

        CyclicBarrier barrier = new CyclicBarrier(NUM_PARTIAL_TASK, new Runnable() {
            private int phase = 0;

            @Override
            public void run() {
                System.out.println(String.format("Phase %1$-3d completed", phase++));
            }
        });

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < NUM_PARTIAL_TASK; i++) {
            es.execute(new CyclicPartialTask(barrier));
        }
        es.shutdown();
    }
}

class CyclicPartialTask implements Runnable {
    private static int          nextId = 0;
    private static Random       rand   = new Random();

    private final int           id     = nextId++;
    private int                 phase  = 0;
    private final CyclicBarrier barrier;              // shared barrier

    public CyclicPartialTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                doWork();
                barrier.await();
                phase++;
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("CyclicPartialTask %1$-3d phase %2$-3d", id, phase);
    }

    private void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this + "completed");
    }
}
