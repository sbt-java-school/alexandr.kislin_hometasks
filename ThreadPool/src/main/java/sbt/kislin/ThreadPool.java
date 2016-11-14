package sbt.kislin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by axel on 18.09.2016.
 */
public class ThreadPool {
    private boolean readyCondition = true;
    private boolean isInterrupt = false;
    private final LinkedList<Runnable> pool = new LinkedList<>();
    private InstanceThread[] threads;
    private static Logger LOGGER = LogManager.getRootLogger();

    public ThreadPool(int threadCount) {
        threads = new InstanceThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new InstanceThread("Thread # " + i);
            threads[i].start();
        }
    }

    public void addTask(Runnable task) {
        if (!poolIsReady()) {
            throw new IllegalStateException("ThreadPool is not ready now, try again later");
        }
        synchronized (pool) {
            pool.add(task);
            pool.notify();
        }
    }

    public void interruptPool() {
        Arrays.stream(threads).forEach(t -> t.interrupt());
        readyCondition = false;
    }

    public boolean poolIsReady() {
        return readyCondition;
    }

    private class InstanceThread extends Thread {

        public InstanceThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!isInterrupt) {
                synchronized (pool) {
                    while (pool.isEmpty()) {
                        try {
                            pool.wait();
                        } catch (InterruptedException e) {
                            LOGGER.info(e);
                            Thread.currentThread().interrupt();
                            isInterrupt = true;
                        }
                    }
                }
            }
            Runnable executor = pool.pollFirst();
            if (executor != null) {
                System.out.println(getName() + " now starting");
                try {
                    executor.run();
                } catch (Exception e) {
                    System.out.println("error in " + e.getMessage());
                }
            }
            isInterrupt = false;
        }
    }
}
