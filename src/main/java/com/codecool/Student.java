package com.codecool;

import java.util.Objects;

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
        return "Name: " + getFirstName() + " " + getLastName() + ", Birth year: " + getYearOfBirth() + ", Gender: " + getGender() + ", Class: "+ whichClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(whichClass, student.whichClass);
    }
}
