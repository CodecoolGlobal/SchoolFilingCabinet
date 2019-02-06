package com.codecool;

import java.util.ArrayList;

public class Dossier {
    private String label;
    private int capacity;
    private ArrayList<Document> docs;

    public Dossier(String label, int capacity) {
        docs = new ArrayList<Document>();
        this.label = label;
        this.capacity = capacity;
    }

    public void addDocs(Document document) {
        if (document.getPages() + getCurrentSize() <= capacity) {
            docs.add(document);
        } else {
            System.out.println("Choose another file or open a new one"); //Throw exception or something.
        }
    }

    public String getLabel() {
        return label;
    }

    public int getCurrentSize() {
        int sum = 0;
        for (Document element : docs) {
            sum += element.getPages();
        }
        return sum;
    }
    @Override
    public String toString() {
        return ("Label: " + label + ", Capacity: "+ capacity);
    }

    public void allDocsInDossier() {
        if (docs.size() == 0 ) {
            System.out.println("No documents added yet in this dossier");
        } else {
            for (Document element : docs) {
                System.out.println(element.toString());
            }
        }
    }

    public void removeDocFromDossier(String name) {
        if (docs.size() == 0 ) {
            System.out.println("No documents added yet in this dossier");
        } else {
            for (Document element : docs) {
                if (element.getLabel().equals(name));
            }
        }
    }
}
