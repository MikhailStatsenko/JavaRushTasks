package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();
    private static final int ORDER_CREATING_INTERVAL = 100;
    public static void main(String[] args) throws InterruptedException {
        List<Tablet> tablets = new ArrayList<>();
        tablets.add(new Tablet(1));
        tablets.add(new Tablet(2));
        tablets.add(new Tablet(3));
        tablets.add(new Tablet(4));
        tablets.add(new Tablet(5));

        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Bob");
        cook1.setQueue(ORDER_QUEUE);
        cook2.setQueue(ORDER_QUEUE);

        new Thread(cook1).start();
        new Thread(cook2).start();

        Thread randOrderThread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));

        randOrderThread.start();
        Thread.sleep(10000);
        randOrderThread.interrupt();

        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
