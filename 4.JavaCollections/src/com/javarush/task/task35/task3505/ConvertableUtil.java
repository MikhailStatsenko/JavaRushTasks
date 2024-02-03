package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <T> Map convert(List<? extends Convertable<T>> list) {
        Map<T, Convertable> result = new HashMap<>();
        for (Convertable el : list) {
            result.put((T) el.getKey(), el);
        }
        return result;
    }
}
