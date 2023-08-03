package com.javarush.task.pro.task13.task1319;

/* 
Месяцы в сезоне
*/

public enum Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;

    public static Month[] getWinterMonths() {
        return new Month[]{Month.DECEMBER, Month.JANUARY, Month.FEBRUARY};
    }
    public static Month[] getSpringMonths() {
        return new Month[]{Month.MARCH, Month.APRIL, Month.MAY};
    }
    public static Month[] getSummerMonths() {
        return new Month[]{Month.JUNE, Month.JULY, Month.AUGUST};
    }
    public static Month[] getAutumnMonths() {
        return new Month[]{Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER};
    }

}
