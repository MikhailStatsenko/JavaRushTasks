package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Solution))
            return false;
        Solution n = (Solution) o;
        if (n.first != null ? !n.first.equals(this.first) : this.first != null) return false;
        return (n.last != null ? n.last.equals(this.last) : this.last == null);
    }

    @Override
    public int hashCode() {
        int firstHash = first == null ? 0 : first.hashCode();
        int lastHash = last == null ? 0 : last.hashCode();
        return  firstHash + lastHash;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
