package com.codecool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class FilingCabinet implements java.io.Serializable {
    private int capacity;
    private ArrayList<Dossier> dossiers;

    public FilingCabinet(int capacity) {
        dossiers = new ArrayList<Dossier>();
        this.capacity = capacity;
    }

    public void addDossier(Dossier dossier) {
        if (dossiers.size() + 1 <= capacity) {
            dossiers.add(dossier);
        } else {
            System.out.println("not enough place");
        }
    }

    public void removeDossier(Dossier dossier) {
        dossiers.remove(dossier);
    }



    public ArrayList<Dossier> getDossierList() {
        return dossiers;
    }


    public void saveMenu() {
        try {
            save();
            System.out.println("Data saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("./FilingCabinet.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

}
