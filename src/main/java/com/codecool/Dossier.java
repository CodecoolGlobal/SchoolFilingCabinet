package com.codecool;

import java.util.ArrayList;

public class Dossier {
    private String label;
    private int capacity;
    private ArrayList<Document> docs;

    public Dossier(String label, int capacity) {
        this.label = label;
        this.capacity = capacity;
    }

    public void addDocs(Document document) {
        if (document.getPages() + getCurrentSize() <= capacity) {
            docs.add(document);
        } else {
            System.out.println("Throw exception, choose another file or open a new one");
        }
    }

    public int getCurrentSize() {
        int sum = 0;
        for (Document element : docs) {
            sum += element.getPages();
        }
        return sum;
    }
}
