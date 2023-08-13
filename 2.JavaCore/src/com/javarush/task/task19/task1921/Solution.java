package com.javarush.task.task19.task1921;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        while (fileReader.ready()) {
            String[] words = fileReader.readLine().split(" ");

            int len = words.length;
            int year = Integer.parseInt(words[len-1]);
            int month = Integer.parseInt(words[len-2]);
            int day = Integer.parseInt(words[len-3]);

            StringBuilder fullName = new StringBuilder();
            for (int i = 0; i < len-3; i++)
                fullName.append(words[i] + " ");
            fullName.deleteCharAt(fullName.length()-1);


            PEOPLE.add(new Person(fullName.toString(), new GregorianCalendar(year, month - 1, day).getTime()));
        }
        fileReader.close();
    }
}
