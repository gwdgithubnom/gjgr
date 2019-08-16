package org.ose.javase.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyFixedSizeExecutor {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            es.submit(new MySimpleTask());
        }
        es.shutdown();
    }
}

class MySimpleTask implements Runnable {
    private static Random rand   = new Random();
    private static int    nextId = 0;

    private final int     id;

    public MySimpleTask() {
        this.id = nextId++;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task " + id + " is running");
            TimeUnit.SECONDS.sleep(rand.nextInt(10));
            System.out.println("Task " + id + " completed");
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
