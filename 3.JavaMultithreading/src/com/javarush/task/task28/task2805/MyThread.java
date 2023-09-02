package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static final AtomicInteger priority = new AtomicInteger();
    private static int getCyclicPriority() {
        return priority.getAndIncrement() % 10 + 1;
    }

    private static int getCyclicPriority(int max) {
        int priority = MyThread.priority.getAndIncrement() % 10 + 1;
        return Math.min(priority, max);
    }
    public MyThread() {
        this.setPriority(getCyclicPriority());
    }

    public MyThread(Runnable task) {
        super(task);
        this.setPriority(getCyclicPriority());
    }

    public MyThread(ThreadGroup group, Runnable task) {
        super(group, task);
        this.setPriority(getCyclicPriority(group.getMaxPriority()));
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(getCyclicPriority());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        this.setPriority(getCyclicPriority(group.getMaxPriority()));
    }

    public MyThread(Runnable task, String name) {
        super(task, name);
        this.setPriority(getCyclicPriority());
    }

    public MyThread(ThreadGroup group, Runnable task, String name) {
        super(group, task, name);
        this.setPriority(getCyclicPriority(group.getMaxPriority()));
    }

    public MyThread(ThreadGroup group, Runnable task, String name, long stackSize) {
        super(group, task, name, stackSize);
        this.setPriority(getCyclicPriority(group.getMaxPriority()));
    }
}
