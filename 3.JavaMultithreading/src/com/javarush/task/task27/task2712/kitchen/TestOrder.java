package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        dishes = new ArrayList<>();
        int dishesAvailable = Dish.values().length;
        int dishCount = (int) (Math.random() * dishesAvailable);
        for (int i = 0; i < dishCount; i++) {
            dishes.add(Dish.values()[(int) (Math.random()*dishesAvailable)]);
        }
    }
}
