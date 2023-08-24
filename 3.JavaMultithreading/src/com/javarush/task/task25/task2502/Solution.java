package com.javarush.task.task25.task2502;

import java.util.*;
import java.util.stream.Collectors;

/* 
Машину на СТО не повезем!
*/

public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            wheels = new ArrayList<>();
            String[] wheelsArr = loadWheelNamesFromDB();
            if (wheelsArr.length != 4) throw new RuntimeException();
            wheels.addAll(Arrays.stream(wheelsArr).map(Wheel::valueOf).collect(Collectors.toList()));
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
