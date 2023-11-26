package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        return softReference == null ? null : softReference.get();
    }

    public AnyObject put(Long key, AnyObject value) {
        if (cacheMap.get(key) == null)
            return null;

        SoftReference<AnyObject> prevRef = cacheMap.get(key);
        AnyObject prev = prevRef.get();
        prevRef.clear();
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        return prev;
    }

    public AnyObject remove(Long key) {
        if (cacheMap.get(key) == null)
            return null;
        SoftReference<AnyObject> softReference = cacheMap.remove(key);
        AnyObject prev = softReference.get();
        softReference.clear();
        return prev;
    }
}