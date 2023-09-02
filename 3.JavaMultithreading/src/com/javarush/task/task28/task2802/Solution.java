package com.javarush.task.task28.task2802;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/

public class Solution {
    public static class AmigoThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private AtomicInteger threadNum = new AtomicInteger();
        private static final AtomicInteger factoryNum = new AtomicInteger();

        public AmigoThreadFactory() {
            namePrefix = "-pool-" +
                    factoryNum.incrementAndGet() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);

            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            thread.setName(thread.getThreadGroup().getName() + namePrefix + threadNum.incrementAndGet());
            return thread;
        }
    }

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = () -> System.out.println(Thread.currentThread().getName());
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
