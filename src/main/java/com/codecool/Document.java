package com.codecool;

public class Document  implements java.io.Serializable {
    private String label;
    private int pages;
    private Person person;

    public Document (String label, int pages, Person person) {
        this.label = label;
        this.pages = pages;
        this.person = person;
    }

    public String getLabel() {
        return label;
    }

    public int getPages() {
        return pages;
    }

    public Person getPerson() {
        return person;
    }
    @Override
    public String toString() {
        return ("Document's name: " + label + ", How many pages: " + pages + ", Whose document it is: " + person.getFirstName() + " " + person.getLastName() );
    }
}
