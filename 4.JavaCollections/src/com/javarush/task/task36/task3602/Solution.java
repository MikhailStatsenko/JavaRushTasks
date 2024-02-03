package com.javarush.task.task36.task3602;

import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println(getExpectedClass());
    }

    public static Class<?> getExpectedClass() throws InstantiationException, IllegalAccessException {
        for (Class clazz : Collections.class.getDeclaredClasses()) {
            if (clazz.getSimpleName().equals("EmptyList")) {
                return clazz;
            }
        }
//        for (Class<?> clazz : Collections.class.getDeclaredClasses()) {
//            if (!List.class.isAssignableFrom(clazz)) {
//                continue;
//            }
//
//            if (!Modifier.isPrivate(clazz.getModifiers()) || !Modifier.isStatic(clazz.getModifiers())) {
//                continue;
//            }
//
//
//            try {
//                clazz.getDeclaredMethod("get", int.class).setAccessible(true);
//                clazz.getDeclaredConstructor().setAccessible(true);
//                Object obj = clazz.newInstance();
//
//                Method method = clazz.getMethod("get", int.class);
//                try {
//                    method.invoke(obj, 1);
//                } catch (InvocationTargetException e) {
//                    if (e.getCause() instanceof IndexOutOfBoundsException) {
//                        return clazz;
//                    }
//                }
//            } catch (NoSuchMethodException | InaccessibleObjectException ignored) { }
//        }
        return null;
    }
}
