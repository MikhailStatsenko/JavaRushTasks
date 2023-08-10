package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        if (args == null || args.length < 2) return;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        SimpleDateFormat outFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        Person person;
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length-2; i += 3) {
                        if (args[i+1].equals("м"))
                            allPeople.add(Person.createMale(args[i], format.parse(args[i+2])));
                        else
                            allPeople.add(Person.createFemale(args[i], format.parse(args[i+2])));
                        System.out.println(allPeople.size() - 1);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        System.out.println(person.getName() + " " + ((person.getSex()).equals(Sex.MALE) ? "м" : "ж") + " " + outFormat.format(person.getBirthDate()));
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length-3; i+=4) {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        person.setName(args[i+1]);
                        person.setSex(args[i+2].equals("м") ? Sex.MALE : Sex.FEMALE);
                        person.setBirthDate(format.parse(args[i+3]));
                    }
                }
                break;
            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        person.setBirthDate(null);
                        person.setName(null);
                        person.setSex(null);
                    }
                }
                break;
        }
    }
}
