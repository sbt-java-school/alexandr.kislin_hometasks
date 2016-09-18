package sbt.kislin;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by axel on 18.09.2016.
 */
public class ThreadPool {
    private boolean readyCondition = true;
    private LinkedList<Runnable> pool = new LinkedList<>();
    private InstanceThread[] threads;

    public ThreadPool(int threadCount) {
        threads = new InstanceThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new InstanceThread("Thread # " + i);
            threads[i].run();
        }
    }

    public void addTask(Runnable task) {
        if (!readyCondition) {
            throw new InvalidStateException("ThreadPool is not ready now, try again later");
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
            waiting:
            while (true) {
                synchronized (pool) {
                    while (pool.isEmpty()) {
                        try {
                            pool.wait();
                        } catch (InterruptedException e) {
                            break waiting;
                        }
                    }
                }
            }
            InstanceThread executor = (InstanceThread) pool.pollFirst();
            if (executor != null) {
                System.out.println(executor.getName() + " now starting");
                try {
                    executor.run();
                } catch (Exception e) {
                    System.out.println("error in " + e.getMessage());
                }
            }
        }
    }
}
