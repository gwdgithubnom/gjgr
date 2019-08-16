package org.ose.javase.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MyDaemon {
    public static void main(String[] args) throws InterruptedException {
        // create daemon thread by hand
        for (int i = 0; i < 2; i++) {
            Thread daemon = new Thread(new DaemonTask());
            daemon.setDaemon(true);
            daemon.start();
        }

        // implements ThreadFactory
        ExecutorService es = Executors.newCachedThreadPool(new MyDaemonThreadFactory());
        for (int i = 0; i < 2; i++) {
            es.execute(new DaemonTask());
        }
        es.shutdown();

        System.out.println("All daemons started.");
        TimeUnit.SECONDS.sleep(10);
    }
}

class DaemonTask implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("[daemon] " + this + " [info] "
                                   + Thread.currentThread().toString());
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            System.err.println("[daemon] " + this + " interrupted!");
        }
    }
}

class MyDaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
