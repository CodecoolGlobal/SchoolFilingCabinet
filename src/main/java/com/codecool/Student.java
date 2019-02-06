package com.codecool;

public class Student extends Person{
    private String whichClass;

    public Student(String firstName, String lastName, int yearOfBirth, Gender gender, String whichClass) {
        super(firstName, lastName, yearOfBirth, gender);
        this.whichClass = whichClass;
    }

    public String getWhichClass() {
        return whichClass;
    }

    @Override
    public String toString() {
        return super(toString()) + "Class: " +
    }
}
