package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        System.out.println("Выберете блюдо из списка: ");
        System.out.println(Dish.allDishesToString());

        List<Dish> dishesForOrder = new ArrayList<>();

        String choice;
        while (!(choice = reader.readLine()).equals("exit")) {
            String finalChoice = choice;
            if (Arrays.stream(Dish.values()).noneMatch(x -> x.name().equalsIgnoreCase(finalChoice))) {
                System.out.println("Такого блюда нет в меню!");
            } else {
                 dishesForOrder.add(Dish.valueOf(choice.toUpperCase()));
            }
        }
        return dishesForOrder;
    }
}
