package com.codecool;

public abstract class Person {
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private Gender gender;

    public Person(String firstName, String lastName, int yearOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public Gender getGender() {
        return gender;
    }


}
