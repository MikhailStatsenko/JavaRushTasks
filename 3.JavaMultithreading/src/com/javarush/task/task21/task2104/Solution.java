package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Solution))
            return false;

        Solution solution = (Solution) obj;

        if (solution.first == null) {
            if (solution.last == null)
                return this.first == null && this.last == null;
            return this.first == null && solution.last.equals(this.last);
        } else if (solution.last == null)
            return this.last == null && solution.first.equals(this.first);

        return solution.first.equals(this.first) && solution.last.equals(this.last);
    }


    @Override
    public int hashCode() {
        int firstHash = first == null ? 0 : first.hashCode();
        int lastHash = last == null ? 0 : last.hashCode();
        return 17 * (31 * firstHash + lastHash);
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
