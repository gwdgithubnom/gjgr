package org.ose.javase.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MySemaphore {
    public static void main(String[] args) {
        final int POOL_SIZE = 3;
        Pool<Object> pool = new Pool<Object>(Object.class, POOL_SIZE);

        ExecutorService es = Executors.newCachedThreadPool();
        final int NUM_TASK = 5;
        for (int i = 0; i < NUM_TASK; i++) {
            es.execute(new HeavyTask<Object>(pool));
        }
        es.shutdown();
    }
}

class Pool<T> {
    private final int       size;
    private final List<T>   items;
    private boolean[]       checkedOut;
    private final Semaphore semaphore;

    public Pool(Class<T> classObject, int size) {
        this.size = size;
        this.items = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            try {
                // assume a default constructor
                items.add(classObject.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        this.checkedOut = new boolean[size];
        this.semaphore = new Semaphore(size, true);
    }

    public T checkOut() throws InterruptedException {
        semaphore.acquire();
        return getItem();
    }

    public void checkIn(T item) {
        if (releaseItem(item)) {
            semaphore.release();
        }
    }

    private synchronized T getItem() {
        for (int i = 0; i < size; i++) {
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }

        return null; // Semaphore prevents reaching here.
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if (index == -1) {
            return false; // item not in the pool
        }
        if (checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }

        return false; // wasn't checked out
    }
}

class HeavyTask<T> implements Runnable {
    private static int    nextId = 0;
    private final int     id     = nextId++;
    private final Pool<T> pool;

    public HeavyTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            System.out.println(this + " checking out");
            T item = pool.checkOut();
            System.out.println(this + " checked out");
            TimeUnit.SECONDS.sleep(10); // do the task
            System.out.println(this + " checking in");
            pool.checkIn(item);
            System.out.println(this + " checked in");
            System.out.println(this + " finished");
        } catch (InterruptedException e) {
            // acceptable way to terminate
        }
    };

    @Override
    public String toString() {
        return String.format("HeavyTask %1$-3d", id);
    }
}
