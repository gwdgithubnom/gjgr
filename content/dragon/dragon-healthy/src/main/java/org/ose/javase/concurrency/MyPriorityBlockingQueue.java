package org.ose.javase.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyPriorityBlockingQueue {
    public static void main(String[] args) {
        final int NUM_DELAYED_TASK = 10;
        Random rand = new Random();

        ExecutorService es = Executors.newCachedThreadPool();

        PriorityBlockingQueue<PriorityTask> queue = new PriorityBlockingQueue<PriorityTask>();
        for (int i = 0; i < NUM_DELAYED_TASK; i++) {
            queue.put(new PriorityTask(rand.nextInt(10)));
        }
        queue.put(new PriorityTask.EndSentinel(es)); // put at the end of the queue to call es.shutdownNow()

        es.execute(new PriorityTaskConsumer(queue));
        es.shutdown();
    }
}

class PriorityTaskConsumer implements Runnable {
    private final PriorityBlockingQueue<PriorityTask> queue;

    public PriorityTaskConsumer(PriorityBlockingQueue<PriorityTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run(); // blocks until an element with an expired delay is available on this queue
            }
        } catch (InterruptedException e) {
            System.out.println("PriorityTaskConsumer interrupted");
        }
        System.out.println("PriorityTaskConsumer finished");
    }
}

class PriorityTask implements Runnable, Comparable<PriorityTask> {
    private static int    nextId = 0;
    private static Random rand   = new Random();

    private final int     id     = nextId++;
    private final int     priority;

    public PriorityTask(int priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        try {
            System.out.println(this);
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
        } catch (InterruptedException e) {
            // acceptable way to terminate
        }
    };

    @Override
    public int compareTo(PriorityTask task) {
        PriorityTask o = (PriorityTask) task;
        return this.priority > o.priority ? -1 : (this.priority == o.priority ? 0 : 1);
    };

    @Override
    public String toString() {
        return String.format("PriorityTask %1$-3d priority = %2$-3d", id, priority);
    }

    public static class EndSentinel extends PriorityTask {
        private final ExecutorService es;

        public EndSentinel(ExecutorService es) {
            super(-1);
            this.es = es;
        }

        @Override
        public void run() {
            es.shutdownNow();
        }
    }
}
