package com.codecool;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Dossier implements java.io.Serializable, Iterable<Document> {
    private String label;
    private int capacity;
    private ArrayList<Document> docs;
    private int filledOut;

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
        return ("Label: " + label + ", Capacity: "+ capacity + ", Filled up to: " + filledOut + " pages");
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
        Document doc = null;
        if (docs.size() == 0 ) {
            System.out.println("No documents added yet in this dossier");
        } else {
            for (Document element : docs) {
                System.out.println(element.getLabel());
                if (element.getLabel().equals(name)) {
                    doc = element;
                }
            }
        }
        if (doc == null ) {
            System.out.println("No documents added yet in this dossier");
        } else {
            docs.remove(doc);
        }
    }

    public void listAll() {
        Iterator<Document> it = docs.iterator();
        while (it.hasNext()) {
            Document doc = it.next();
            filledOut += doc.getPages();
            System.out.println(doc);
        }
    }

    @Override
    public Iterator<Document> iterator() {
        return docs.iterator();

    }

    public ArrayList<Document> getDocs() {
        return docs;
    }
}
