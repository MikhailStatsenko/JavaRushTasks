package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V obj = cache.get(key);
        if (obj != null)
            return obj;

        Constructor<V>[] constructors = (Constructor<V>[]) clazz.getDeclaredConstructors();
        Constructor<V> constructor = Arrays.stream(constructors).filter(x -> x.getParameterTypes().length == 1 && x.getParameterTypes()[0].equals(key.getClass())).findAny().get();
        return cache.put(key, constructor.newInstance(key));
    }

    public boolean put(V obj) {
        Method getKeyMethod = null;
        try {
            getKeyMethod = obj.getClass().getDeclaredMethod("getKey");
            getKeyMethod.setAccessible(true);
            cache.put((K) getKeyMethod.invoke(obj), obj);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return false;
        }
        return true;
    }

    public int size() {
        return cache.size();
    }
}
