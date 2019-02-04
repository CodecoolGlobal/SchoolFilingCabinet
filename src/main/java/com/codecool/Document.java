package com.codecool;

public class Document {
    private int pages;
    private Person person;

    public Document(int pages, Person person) {
        this.pages = pages;
        this.person = person;
    }

    public int getPages() {
        return pages;
    }

    public Person getPerson() {
        return person;
    }
}
