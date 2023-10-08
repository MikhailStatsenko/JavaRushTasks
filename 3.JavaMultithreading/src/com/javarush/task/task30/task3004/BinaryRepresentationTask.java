package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private final int x;

    @Override
    protected String compute() {
        if (x < 2)
            return Integer.toString(x % 2);
        return new BinaryRepresentationTask(x/2).fork().join() + x % 2;
    }

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }
}
