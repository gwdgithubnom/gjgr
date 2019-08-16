package org.ose.javase.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueue<T> {
    private Queue<T>     items    = new LinkedList<T>();
    private final int    CAPACITY;

    /** Lock held by put, offer, etc */
    private final Object putLock  = new Object();

    /** Lock held by take, poll, etc */
    private final Object takeLock = new Object();

    public BlockingQueue(int capacity) {
        this.CAPACITY = capacity;
    }

    public void put(T item) throws InterruptedException {
        synchronized (putLock) {
            while (items.size() == CAPACITY) {
                putLock.wait();
            }

            items.add(item);
            System.out.println("put: " + items);

            if (items.size() < CAPACITY) {
                putLock.notifyAll();
            }
        }

        if (items.size() == 1) {
            synchronized (takeLock) {
                takeLock.notifyAll();
            }
        }
    }

    public T take() throws InterruptedException {
        T item;

        synchronized (takeLock) {
            while (items.size() == 0) {
                takeLock.wait();
            }

            item = items.remove();
            System.out.println("take: " + items);

            if (items.size() > 0) {
                takeLock.notifyAll();
            }
        }

        if (items.size() == CAPACITY - 1) {
            synchronized (putLock) {
                putLock.notifyAll();
            }
        }

        return item;
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new BlockingQueue<Integer>(5);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new PutTask(bq));
        es.execute(new PutTask(bq));
        es.execute(new TakeTask(bq));
        es.shutdown();
    }

    private static class PutTask implements Runnable {
        private static final AtomicInteger   id = new AtomicInteger(0);
        private final int                    taskId;
        private final BlockingQueue<Integer> bq;

        public PutTask(BlockingQueue<Integer> bq) {
            taskId = id.getAndIncrement();
            this.bq = bq;
        }

        @Override
        public void run() {
            try {
                for (int i = 10 * taskId; i < 10 * taskId + 10; i++) {
                    bq.put(i);
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class TakeTask implements Runnable {
        private static final AtomicInteger   id = new AtomicInteger(0);
        private final int                    taskId;
        private final BlockingQueue<Integer> bq;

        public TakeTask(BlockingQueue<Integer> bq) {
            taskId = id.getAndIncrement();
            this.bq = bq;
        }

        @Override
        public void run() {
            try {
                for (int i = taskId * 10; i < taskId * 10 + 20; i++) {
                    Integer item = bq.take();
                    TimeUnit.MILLISECONDS.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
