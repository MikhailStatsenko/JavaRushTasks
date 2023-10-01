package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private int interval;
    private List<Tablet> tablets;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Tablet tablet = tablets.get((int) (Math.random() * tablets.size()));
                TestOrder order = new TestOrder(tablet);
                Thread.sleep(interval);
            } catch (InterruptedException | IOException ignored) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
