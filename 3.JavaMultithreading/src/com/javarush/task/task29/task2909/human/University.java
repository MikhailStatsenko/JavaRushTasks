package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class University {
    private int age;
    private String name;
    private List<Student> students;

    public University(String name, int age) {
        this.students = new ArrayList<>();
        this.age = age;
        this.name = name;
    }

    public Student getStudentWithAverageGrade(double grade) {
        return students.stream()
                .filter(student -> student.getAverageGrade() == grade)
                .findFirst().orElse(null);
    }

    public Student getStudentWithMaxAverageGrade() {
        return students.stream()
                .max(Comparator.comparingDouble(Student::getAverageGrade)).orElse(null);
    }

    public Student getStudentWithMinAverageGrade() {
        return students.stream()
                .min(Comparator.comparingDouble(Student::getAverageGrade)).orElse(null);
    }

    public void expel(Student student) {
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> studentList) {
        this.students = studentList;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}