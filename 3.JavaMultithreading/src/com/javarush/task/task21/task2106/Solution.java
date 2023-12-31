package com.javarush.task.task21.task2106;

import java.util.Date;

/* 
Ошибка в equals/hashCode
*/

public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;

        Solution solution1 = (Solution) o;

        return solution1.anInt == this.anInt && solution1.aDouble == this.aDouble
                && (solution1.string == this.string || solution1.string != null && solution1.string.equals(this.string))
                && (solution1.date == this.date || solution1.date != null && solution1.date.equals(this.date))
                && (solution1.solution == this.solution || solution1.solution != null && solution1.solution.equals(this.solution));
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = anInt;
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {

    }
}
