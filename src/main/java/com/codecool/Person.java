package com.codecool;

import java.util.Objects;

public abstract class Person  implements java.io.Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return yearOfBirth == person.yearOfBirth &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                gender == person.gender;
    }
}
