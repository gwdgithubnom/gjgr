package org.ose.javase.concurrency;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyScheduledExecutor {
    public static void main(String[] args) {
        Controller controller = new Controller(new ScheduledThreadPoolExecutor(10));
        controller.schedule(controller.new Alert(), 500, TimeUnit.MILLISECONDS);
        controller.repeat(controller.new DayMode(), 1000, 1000, TimeUnit.MILLISECONDS);
        controller.repeat(controller.new NightMode(), 1500, 1000, TimeUnit.MILLISECONDS);
        controller.schedule(controller.new Terminate(), 10000, TimeUnit.MILLISECONDS);
    }
}

class Controller {
    private ScheduledThreadPoolExecutor scheduler;
    private String                      mode = "day";

    public Controller(ScheduledThreadPoolExecutor scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(Runnable event, long delay, TimeUnit unit) {
        scheduler.schedule(event, delay, unit);
    }

    public void repeat(Runnable event, long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(event, initialDelay, period, unit);
    }

    public synchronized String getMode() {
        return mode;
    }

    public synchronized void setMode(String mode) {
        this.mode = mode;
    }

    class Alert implements Runnable {
        @Override
        public void run() {
            System.out.println("Alert!");
        }
    }

    class DayMode implements Runnable {
        @Override
        public void run() {
            System.out.println("setting mode to 'day'");
            setMode("day");
        }
    }

    class NightMode implements Runnable {
        @Override
        public void run() {
            System.out.println("setting mode to 'night'");
            setMode("night");
        }
    }

    class Terminate implements Runnable {
        @Override
        public void run() {
            System.out.println("terminating");
            scheduler.shutdownNow();

            // must start a separate task to do this job since the scheduler has been shutdown.
            new Thread() {
                @Override
                public void run() {
                    System.out.println("terminated");
                };
            }.start();
        }
    }
}
