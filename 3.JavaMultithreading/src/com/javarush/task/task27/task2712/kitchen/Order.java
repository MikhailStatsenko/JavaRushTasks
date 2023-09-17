package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime() {
        return dishes.stream().mapToInt(Dish::getDuration).sum();
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder dishNameList = new StringBuilder();
        for (Dish dish : dishes) {
            dishNameList.append(dish.name()).append(", ");
        }
        if (dishNameList.length() > 1)
            dishNameList.delete(dishNameList.length()-2, dishNameList.length());
        return String.format("Your order: [%s] of %s, cooking time %dmin", dishNameList, tablet, getTotalCookingTime());
    }
}
