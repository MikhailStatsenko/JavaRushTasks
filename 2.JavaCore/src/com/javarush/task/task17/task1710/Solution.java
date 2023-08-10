package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        if (args.length < 2) return;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        SimpleDateFormat outFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        if (args[0].equals("-c")) {
            if (args[2].equals("м"))
                allPeople.add(Person.createMale(args[1], format.parse(args[3])));
            else
                allPeople.add(Person.createFemale(args[1], format.parse(args[3])));
            System.out.println(allPeople.size()-1);
            return;
        }

        Person person = allPeople.get(Integer.parseInt(args[1]));
        if (args[0].equals("-r")) {
            System.out.println(person.getName() + " " + ((person.getSex()).equals(Sex.MALE) ? "м" : "ж") + " " + outFormat.format(person.getBirthDate()));
        }
        else if (args[0].equals("-u")) {
            person.setName(args[2]);
            person.setSex(args[3].equals("м") ? Sex.MALE : Sex.FEMALE);
            person.setBirthDate(format.parse(args[4]));
        }
        else if (args[0].equals("-d")) {
            person.setBirthDate(null);
            person.setName(null);
            person.setSex(null);
        }
    }
}
