package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;

        Scanner sc = new Scanner(System.in);

        while (true) {
            key = sc.nextLine();
            if (key.equals("user"))
                person = new Person.User();
            else if (key.equals("loser"))
                person = new Person.Loser();
            else if (key.equals("coder"))
                person = new Person.Coder();
            else if (key.equals("proger"))
                person = new Person.Proger();
            else break;

            doWork(person);
        }
    }

    public static void doWork(Person person) {
        if (person instanceof Person.User)
            ((Person.User) person).live();
        else if (person instanceof Person.Loser)
            ((Person.Loser) person).doNothing();
        else if (person instanceof Person.Coder)
            ((Person.Coder) person).writeCode();
        else
            ((Person.Proger) person).enjoy();
    }
}
