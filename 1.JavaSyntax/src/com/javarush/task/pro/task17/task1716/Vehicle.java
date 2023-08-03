package com.javarush.task.pro.task17.task1716;

/* 
Дорожное движение
*/

public interface Vehicle {
    void move();
    default public void start() {
        System.out.println("Начинаю движение.");
    }

    default public void stop() {
        System.out.println("Останавливаюсь.");
    }
}
