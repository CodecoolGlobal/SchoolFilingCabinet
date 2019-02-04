package com.codecool;

public class Teacher extends Person {
    private int wage;

    public Teacher(String firstName, String lastName, int yearOfBirth, Gender gender, int wage) {
        super(firstName, lastName, yearOfBirth, gender);
        this.wage = wage;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }
}
