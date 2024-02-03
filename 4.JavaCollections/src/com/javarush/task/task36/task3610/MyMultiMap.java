package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;
        for (K key : map.keySet()) {
            size += map.get(key).size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        if (!map.containsKey(key)) {
            List<V> valList = new ArrayList<>(repeatCount);
            valList.add(value);
            map.put(key, valList);
            return null;
        }

        List<V> valList = map.get(key);
        if (valList.size() >= repeatCount) {
            valList.remove(0);
        }

        valList.add(value);
        return valList.get(valList.size()-2);
    }

    @Override
    public V remove(Object key) {
        List<V> valList = map.get(key);
        if (valList == null) {
            return null;
        }

        if (valList.size() > 1) {
            return valList.remove(0);
        }
        List<V> removed = map.remove(key);
        return removed.get(0);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (K key : map.keySet()) {
            values.addAll(map.get(key));
        }
        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (K key : map.keySet()) {
           boolean contains = map.get(key).contains(value);
           if (contains) {
               return true;
           }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}