package org.ose.javase.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer {
    public static void main(String[] args) {
        Queue<Integer> taskQueue = new LinkedList<Integer>();
        int capacity = 5;
        Thread tProducer = new Thread(new Producer(capacity, taskQueue));
        Thread tConsumer = new Thread(new Consumer(taskQueue));
        tProducer.start();
        tConsumer.start();
    }
}

class Producer implements Runnable {
    private final int            CAPACITY;
    private final Queue<Integer> taskQueue;

    public Producer(int capacity, Queue<Integer> sharedQueue) {
        this.CAPACITY = capacity;
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (!Thread.interrupted()) {
            try {
                produce(counter++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void produce(int item) throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.size() == CAPACITY) {
                System.out.println("Queue is full " + Thread.currentThread().getName()
                                   + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }
        }

        // simulate producing an item
        TimeUnit.MILLISECONDS.sleep(500);

        synchronized (taskQueue) {
            taskQueue.add(item);
            taskQueue.notifyAll(); // wake up the consumers
        }

        System.out.println("Produced: " + item);
    }
}

class Consumer implements Runnable {
    private final Queue<Integer> taskQueue;

    public Consumer(Queue<Integer> sharedQueue) {
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                System.out.println("Queue is empty " + Thread.currentThread().getName()
                                   + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }
        }

        int item;
        synchronized (taskQueue) {
            item = taskQueue.remove();
            taskQueue.notifyAll(); // wake up the consumers
        }

        // simulate consuming the item
        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("Consumed: " + item);
    }
}
