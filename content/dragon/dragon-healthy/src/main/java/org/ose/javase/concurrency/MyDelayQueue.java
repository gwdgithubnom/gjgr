package org.ose.javase.concurrency;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyDelayQueue {
    public static void main(String[] args) {
        final int NUM_DELAYED_TASK = 10;
        Random rand = new Random();

        ExecutorService es = Executors.newCachedThreadPool();

        DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
        for (int i = 0; i < NUM_DELAYED_TASK; i++) {
            queue.put(new DelayedTask(rand.nextInt(5000)));
        }
        queue.put(new DelayedTask.EndSentinel(5000, es)); // put at the end of the queue to call es.shutdownNow()

        es.execute(new DelayedTaskConsumer(queue));
        es.shutdown();
    }
}

class DelayedTaskConsumer implements Runnable {
    private final DelayQueue<DelayedTask> queue;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run(); // blocks until an element with an expired delay is available on this queue
            }
        } catch (InterruptedException e) {
            System.out.println("DelayedTaskConsumer interrupted");
        }
        System.out.println("DelayedTaskConsumer finished");
    }
}

class DelayedTask implements Runnable, Delayed {
    private static int nextId = 0;

    private final int  id     = nextId++;
    private final int  delayInMilliSeconds;
    private final long triggerTime;

    public DelayedTask(int delayInMilliSeconds) {
        this.delayInMilliSeconds = delayInMilliSeconds;
        this.triggerTime = System.nanoTime()
                           + TimeUnit.NANOSECONDS.convert(delayInMilliSeconds,
                               TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        System.out.println(this);
    };

    @Override
    public int compareTo(Delayed delayed) {
        DelayedTask o = (DelayedTask) delayed;
        return this.triggerTime < o.triggerTime ? -1 : (this.triggerTime == o.triggerTime ? 0 : 1);
    };

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(triggerTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    };

    @Override
    public String toString() {
        return String.format("DelayedTask %1$-3d delayInMilliSeconds = %2$-3d", id,
            delayInMilliSeconds);
    }

    public static class EndSentinel extends DelayedTask {
        private final ExecutorService es;

        public EndSentinel(int delayInMilliSeconds, ExecutorService es) {
            super(delayInMilliSeconds);
            this.es = es;
        }

        @Override
        public void run() {
            es.shutdownNow();
        }
    }
}
