package org.ose.javase.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++) {
            futures.add(es.submit(new CallableTask(5)));
        }
        for (Future<String> f : futures) {
            try {
                System.out.println(f.get()); // the return value of call()
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                es.shutdown();
            }
        }
    }
}

class CallableTask implements Callable<String> {
    private int countDown;

    public CallableTask(int countDown) {
        this.countDown = countDown;
    }

    @Override
    public String call() {
        while (--countDown > 0) {
            System.out.println(this);
            Thread.yield();
        }

        return "Finished: " + this;
    }

    @Override
    public String toString() {
        return Thread.currentThread().toString() + ": " + countDown;
    }
}
