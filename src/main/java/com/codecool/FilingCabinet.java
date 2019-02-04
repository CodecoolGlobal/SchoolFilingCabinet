package com.codecool;

import java.util.ArrayList;

public class FilingCabinet {
    private int capacity;
    private ArrayList<Dossier> dossiers;

    public FilingCabinet(int capacity) {
        dossiers = new ArrayList<Dossier>();
        this.capacity = capacity;
    }

    public void add(Dossier dossier) {
        if (dossiers.size() + 1 <= capacity) {
            dossiers.add(dossier);
        } else {
            System.out.println("not enough place");
        }
    }
}
